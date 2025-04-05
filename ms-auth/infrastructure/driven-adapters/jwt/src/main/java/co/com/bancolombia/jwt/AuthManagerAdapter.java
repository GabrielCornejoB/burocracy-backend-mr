package co.com.bancolombia.jwt;

import co.com.bancolombia.model.authentication.UserLogin;
import co.com.bancolombia.model.authentication.gateways.AuthManagerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthManagerAdapter implements AuthManagerGateway {

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder encoder;

    @Override
    public String authenticate(UserLogin user) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.cc(), user.password())
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }

    @Override
    public String encode(String password) {
        return this.encoder.encode(password);
    }

}
