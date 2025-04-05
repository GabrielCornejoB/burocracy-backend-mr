package co.com.bancolombia.jwt;

import co.com.bancolombia.utils.enums.HttpStatusCode;
import co.com.bancolombia.utils.exceptions.GeneralException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@NoArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final String AUTH_HEADER_NAME = "Authorization";
    private static final String AUTH_HEADER_PREFIX = "Bearer ";
    private static final Integer AUTH_HEADER_SUBSTRING_INDEX = 7;

    @Autowired
    private JwtAdapter jwtAdapter;

    @Autowired
    private UserDetailsAdapter userDetailsAdapter;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = this.parseJwt(request);
            if (jwt != null && jwtAdapter.validateToken(jwt)) {
                String cc = jwtAdapter.getUsernameFromToken(jwt);
                UserDetails userDetails = userDetailsAdapter.loadUserByUsername(cc);
                var auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            throw new GeneralException("El token no es valido", HttpStatusCode.BAD_REQUEST);
        }
        filterChain.doFilter(request, response);
    }


    String parseJwt(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTH_HEADER_NAME);
        if (authHeader != null && authHeader.startsWith(AUTH_HEADER_PREFIX)) {
            return authHeader.substring(AUTH_HEADER_SUBSTRING_INDEX);
        }
        return null;
    }
}
