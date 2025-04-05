package co.com.bancolombia.api;

import co.com.bancolombia.api.requests.LoginRequest;
import co.com.bancolombia.api.requests.RegisterRequest;
import co.com.bancolombia.usecase.authentication.AuthenticationUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AuthenticationRestController {

    private final AuthenticationUseCase useCase;

    @PostMapping(path = "register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        var result = this.useCase.register(RegisterRequest.toModel(request));

        return ResponseEntity.ok("Usuario con cc: " + result.getCc() + " registrado exitosamente");
    }

    @PostMapping(path = "login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        var token = this.useCase.login(LoginRequest.toModel(request));
        System.out.println(token);
        return ResponseEntity.ok(token);
    }

}
