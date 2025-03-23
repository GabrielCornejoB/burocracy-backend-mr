package co.com.bancolombia.api;

import co.com.bancolombia.model.authentication.User;
import co.com.bancolombia.usecase.authentication.AuthenticationUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AuthenticationRestController {

    private final AuthenticationUseCase useCase;

    @GetMapping(path = "/authentication/register")
    public ResponseEntity<User> register() {
        return null;
    }

}
