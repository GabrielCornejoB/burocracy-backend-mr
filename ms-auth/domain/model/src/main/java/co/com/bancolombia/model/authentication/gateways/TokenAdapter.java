package co.com.bancolombia.model.authentication.gateways;

public interface TokenAdapter {

    String generateToken(String username);

    String getUsernameFromToken(String token);

    Boolean validateToken(String token);

}
