package co.com.bancolombia.jwt;

import co.com.bancolombia.model.authentication.gateways.TokenAdapter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtAdapter implements TokenAdapter {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration-time}")
    private Long expirationTime;

    @Override
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + expirationTime))
                .signWith(this.getSecretKey())
                .compact();
    }

    @Override
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .decryptWith(this.getSecretKey()).build()
                .parseSignedClaims(token)
                .getPayload().getSubject();
    }

    @Override
    public Boolean validateToken(String token) {
        try {
            Jwts.parser().decryptWith(this.getSecretKey()).build().parseSignedClaims(token);
            return Boolean.TRUE;
//        } catch (UnsupportedJwtException e) {
//            throw new GeneralException("El token JWT enviado no está soportado", HttpStatusCode.BAD_REQUEST);
//        } catch (JwtException e) {
//            throw new GeneralException("El token JWT enviado no pudo ser parseado", HttpStatusCode.BAD_REQUEST);
//        } catch (IllegalArgumentException e) {
//            throw new GeneralException("El token JWT no fue enviado o se encuentra vacío", HttpStatusCode.BAD_REQUEST);
        } catch (Exception e) {
            return false;
        }
    }

    SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(this.secretKey.getBytes(StandardCharsets.UTF_8));
    }

}
