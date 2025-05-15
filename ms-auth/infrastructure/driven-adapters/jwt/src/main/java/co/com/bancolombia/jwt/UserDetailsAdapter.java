package co.com.bancolombia.jwt;

import co.com.bancolombia.model.authentication.UserModel;
import co.com.bancolombia.model.authentication.gateways.AuthenticationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserDetailsAdapter implements UserDetailsService {

    private final AuthenticationRepository authenticationRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = this.authenticationRepository.findByCc(username);

        if (user == null) {
            throw new UsernameNotFoundException("No se encontró al usuario con cc: " + username);
        }

        return new User(user.getCc(), user.getPassword(), Collections.emptyList() /* TODO: Acá irían los roles */);
    }
// TODO: Refactor, esto no es un adapter de JWT sino de spring security, o creo que en la misma capa de application
}
