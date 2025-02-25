package datn.example.datn.config.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        setAuthenticationManager(authenticationManager); // Cấu hình AuthenticationManager
    }
}
