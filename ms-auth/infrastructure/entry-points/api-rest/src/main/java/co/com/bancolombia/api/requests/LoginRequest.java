package co.com.bancolombia.api.requests;

import co.com.bancolombia.model.authentication.UserLogin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;

@Value
@Builder
@Jacksonized
public class LoginRequest {

    @NotBlank
    @Pattern(regexp = "^.{8}$|^.{11}$\n")
    String cc;

    @NotBlank
    @Length(min = 8, max = 20)
    String password;

    public static UserLogin toModel(LoginRequest request) {
        return new UserLogin(request.getCc(), request.getPassword());
    }
}
