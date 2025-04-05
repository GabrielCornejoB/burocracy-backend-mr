package co.com.bancolombia.model.authentication.gateways;

import co.com.bancolombia.model.authentication.UserLogin;

public interface AuthManagerGateway {

    String authenticate(UserLogin user);

    String encode(String password);

}
