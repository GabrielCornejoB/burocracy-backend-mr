package co.com.bancolombia.usecase.authentication;

import co.com.bancolombia.model.authentication.UserLogin;
import co.com.bancolombia.model.authentication.UserModel;
import co.com.bancolombia.model.authentication.gateways.AuthManagerGateway;
import co.com.bancolombia.model.authentication.gateways.AuthenticationRepository;
import co.com.bancolombia.model.authentication.gateways.TokenGateway;
import co.com.bancolombia.utils.enums.HttpStatusCode;
import co.com.bancolombia.utils.exceptions.GeneralException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationUseCase {

    private final AuthenticationRepository authenticationRepository;

    private final AuthManagerGateway authManagerGateway;

    private final TokenGateway tokenGateway;

    public UserModel register(UserModel model) {
        var existingUser = this.authenticationRepository.findByCc(model.getCc());

        if (existingUser != null) {
            throw new GeneralException(
                    "El usuario con la cédula " + model.getCc() + " ya se encuentra registrado.",
                    HttpStatusCode.CONFLICT
            );
        }
        model.setPassword(this.authManagerGateway.encode(model.getPassword()));

        return this.authenticationRepository.createUser(model);
    }

    public String login(UserLogin credentials) {
        var auth = this.authManagerGateway.authenticate(credentials);

        return this.tokenGateway.generateToken(auth);
    }

}
