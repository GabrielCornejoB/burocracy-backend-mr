package co.com.bancolombia.usecase.authentication;

import co.com.bancolombia.model.authentication.UserLogin;
import co.com.bancolombia.model.authentication.UserModel;
import co.com.bancolombia.model.authentication.gateways.AuthenticationRepository;
import co.com.bancolombia.utils.enums.HttpStatusCode;
import co.com.bancolombia.utils.exceptions.GeneralException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationUseCase {

    private final AuthenticationRepository authenticationRepository;

    public UserModel register(UserModel model) {
        var existingUser = this.authenticationRepository.findByCc(model.getCc());

        if (existingUser != null) {
            throw new GeneralException(
                    "El usuario con la cédula " + model.getCc() + " ya se encuentra registrado.",
                    HttpStatusCode.CONFLICT
            );
        }

        return this.authenticationRepository.createUser(model);
    }

    public UserModel login(UserLogin credentials) {
        var result = this.authenticationRepository.validateCredentials(credentials);

        if (result == null) {
            throw new GeneralException("Las credenciales no son validas", HttpStatusCode.BAD_REQUEST);
        }

        return result;
    }

}
