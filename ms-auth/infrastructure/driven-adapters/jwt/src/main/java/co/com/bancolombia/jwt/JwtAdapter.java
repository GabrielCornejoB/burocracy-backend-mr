package co.com.bancolombia.jwt;

import co.com.bancolombia.model.authentication.gateways.TokenGateway;
import co.com.bancolombia.utils.enums.HttpStatusCode;
import co.com.bancolombia.utils.exceptions.GeneralException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@Service
public class JwtAdapter implements TokenGateway {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration-time}")
    private Long expirationTime;

    @Override
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expirationTime))
                .signWith(this.getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getSecretKey()).build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    @Override
    public Boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(this.getSecretKey()).build().parseClaimsJws(token);
            return Boolean.TRUE;
        } catch (ExpiredJwtException e) {
            throw new GeneralException("El token enviado está expirado", HttpStatusCode.BAD_REQUEST);
        } catch (UnsupportedJwtException e) {
            throw new GeneralException("El token JWT no está soportado", HttpStatusCode.BAD_REQUEST);
        } catch (MalformedJwtException e) {
            throw new GeneralException("El token JWT no es valido", HttpStatusCode.BAD_REQUEST);
        } catch (IllegalArgumentException e) {
            throw new GeneralException("Los claims del token JWT están vacíos", HttpStatusCode.BAD_REQUEST);
        }
    }

    SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(this.secretKey.getBytes(StandardCharsets.UTF_8));
    }

}
