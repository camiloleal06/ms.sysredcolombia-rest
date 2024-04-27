package ms.sysredcolombia.rest.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import ms.sysredcolombia.rest.modelo.entidades.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static ms.sysredcolombia.rest.security.filters.TokenJwtConfig.CONTENT_TYPE;
import static ms.sysredcolombia.rest.security.filters.TokenJwtConfig.HEADER_AUTHORIZATION;
import static ms.sysredcolombia.rest.security.filters.TokenJwtConfig.KEY_SECRET;
import static ms.sysredcolombia.rest.security.filters.TokenJwtConfig.PREFIX_TOKEN;

@Getter
@Setter
@Slf4j
public class JwtAuthFilter extends UsernamePasswordAuthenticationFilter {

    public JwtAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    private AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {

        UserEntity userEntity = null;
        try {
            userEntity = new ObjectMapper().readValue(request.getInputStream(),
                     UserEntity.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userEntity.getUsername(), userEntity.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();

        Claims claims = Jwts.claims().add("authorities",
                new ObjectMapper().writeValueAsString(
                        authResult.getAuthorities())).build();

        String token = Jwts.builder().subject(user.getUsername()).claims(claims)
                .signWith(KEY_SECRET).compact();

        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);

        Map<String, String> payload = new HashMap<>();
        payload.put("token", token);
        payload.put("username", user.getUsername());
        payload.put("message",
                String.format("Has iniciado sesion %s", user.getUsername()));

        response.getWriter()
                .write(new ObjectMapper().writeValueAsString(payload));
        response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpStatus.OK.value());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException failed)
            throws IOException {
        Map<String, String> payload = new HashMap<>();
        payload.put("message", "Usuario o password incorrectos");
        payload.put("error", failed.getMessage());
        response.getWriter()
                .write(new ObjectMapper().writeValueAsString(payload));
        response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

    public String generateAccesToken(String username){

        return  Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()*Long.parseLong("2000")))
                .signWith(KEY_SECRET)
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parser().verifyWith(KEY_SECRET).build()
                    .parseSignedClaims(token).getPayload();
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }
}

