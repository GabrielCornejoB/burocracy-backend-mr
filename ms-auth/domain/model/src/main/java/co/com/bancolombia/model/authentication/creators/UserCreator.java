package co.com.bancolombia.model.authentication.creators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserCreator {
    private String cc;
    private String password;
    private String firstName;
    private String lastName;
    private Date birthDate;
}
