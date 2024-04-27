package ms.sysredcolombia.rest.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static ms.sysredcolombia.rest.security.filters.TokenJwtConfig.CONTENT_TYPE;
import static ms.sysredcolombia.rest.security.filters.TokenJwtConfig.HEADER_AUTHORIZATION;
import static ms.sysredcolombia.rest.security.filters.TokenJwtConfig.KEY_SECRET;
import static ms.sysredcolombia.rest.security.filters.TokenJwtConfig.PREFIX_TOKEN;

public class JwtValidationFilter extends BasicAuthenticationFilter {
    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String header = request.getHeader(HEADER_AUTHORIZATION);

        if (null == header || !header.startsWith(PREFIX_TOKEN)) {
            chain.doFilter(request,response);
            return;
        }

        String token = header.replace(PREFIX_TOKEN, "");

        try {

            Claims claims = Jwts.parser().verifyWith(KEY_SECRET).build()
                    .parseSignedClaims(token).getPayload();

            String username = claims.getSubject();
            Object autorithiesClaims = claims.get("authorities");

            Collection<? extends GrantedAuthority> authorities = Arrays.asList(
                    new ObjectMapper().addMixIn(SimpleGrantedAuthority.class,
                                    SimpleGrantedAuthorityJsonCreator.class)
                            .readValue(autorithiesClaims.toString().getBytes(),
                                    SimpleGrantedAuthority[].class));

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    username, null, authorities);
            SecurityContextHolder.getContext()
                    .setAuthentication(usernamePasswordAuthenticationToken);
            chain.doFilter(request, response);

        } catch (JwtException e) {

            Map<String, String> payload = new HashMap<>();
                        payload.put("error", e.getMessage());
            response.getWriter()
                    .write(new ObjectMapper().writeValueAsString(payload));
            response.setContentType(CONTENT_TYPE);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }

    }
}
