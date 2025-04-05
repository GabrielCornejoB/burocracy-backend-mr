package co.com.bancolombia.model.authentication.gateways;

public interface TokenGateway {

    String generateToken(String username);

    String getUsernameFromToken(String token);

    Boolean validateToken(String token);

}
