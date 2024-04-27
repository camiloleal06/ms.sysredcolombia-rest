package ms.sysredcolombia.rest.security.filters;

import io.jsonwebtoken.Jwts;
import lombok.experimental.UtilityClass;

import javax.crypto.SecretKey;
@UtilityClass
public class TokenJwtConfig {
    public static final SecretKey KEY_SECRET = Jwts.SIG.HS256.key().build();
    public static  final String PREFIX_TOKEN ="Bearer ";
    public static final String HEADER_AUTHORIZATION ="Authorization";
    public static final String CONTENT_TYPE = "application/json";
}
