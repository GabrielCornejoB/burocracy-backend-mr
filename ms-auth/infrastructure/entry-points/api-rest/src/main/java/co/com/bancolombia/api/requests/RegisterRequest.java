package co.com.bancolombia.api.requests;

import co.com.bancolombia.model.authentication.creators.UserCreator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Getter
@Setter
@Builder
public class RegisterRequest {
    @NotBlank
    @Pattern(regexp = "^.{8}$|^.{11}$\n")
    private String cc;

    @NotBlank
    @Length(min = 8, max = 20)
    private String password;

    @NotBlank
    @Length(min = 3, max = 50)
    private String firstName;

    @NotBlank
    @Length(min = 3, max = 50)
    private String lastName;

    @NotNull
    private Date birthDate;

    public static UserCreator toCreator(RegisterRequest registerRequest) {
        return UserCreator.builder()
                .cc(registerRequest.getCc())
                .password(registerRequest.getPassword())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .birthDate(registerRequest.getBirthDate())
                .build();
    }
}
