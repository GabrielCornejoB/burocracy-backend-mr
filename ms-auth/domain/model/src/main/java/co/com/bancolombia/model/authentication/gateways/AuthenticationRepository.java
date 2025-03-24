package co.com.bancolombia.model.authentication.gateways;

import co.com.bancolombia.model.authentication.UserLogin;
import co.com.bancolombia.model.authentication.UserModel;

public interface AuthenticationRepository {

    UserModel createUser(UserModel creator);

    UserModel findByCc(String cc);

    UserModel validateCredentials(UserLogin credentials);

}
