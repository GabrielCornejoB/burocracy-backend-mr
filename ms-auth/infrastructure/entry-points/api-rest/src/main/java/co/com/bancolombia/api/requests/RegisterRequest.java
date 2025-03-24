package co.com.bancolombia.api.requests;

import co.com.bancolombia.model.authentication.UserModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Value
@Builder
@Jacksonized
public class RegisterRequest {
    @NotBlank
    @Pattern(regexp = "^.{8}$|^.{11}$\n")
    String cc;

    @NotBlank
    @Length(min = 8, max = 20)
    String password;

    @NotBlank
    @Length(min = 3, max = 50)
    String firstName;

    @NotBlank
    @Length(min = 3, max = 50)
    String lastName;

    @NotNull
    Date birthDate;

    public static UserModel toModel(RegisterRequest registerRequest) {
        return UserModel.builder()
                .cc(registerRequest.getCc())
                .password(registerRequest.getPassword())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .birthDate(registerRequest.getBirthDate())
                .build();
    }

}
