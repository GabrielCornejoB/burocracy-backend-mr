package co.com.bancolombia.model.authentication.gateways;

import co.com.bancolombia.model.authentication.User;
import co.com.bancolombia.model.authentication.creators.UserCreator;

public interface AuthenticationRepository {

    User createUser(UserCreator creator);

}
