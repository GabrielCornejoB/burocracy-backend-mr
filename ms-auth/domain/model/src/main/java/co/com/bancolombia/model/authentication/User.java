package co.com.bancolombia.model.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class User {
    private UUID id;
    private String cc;
    private String firstName;
    private String lastName;
    private Date birthDate;
}
