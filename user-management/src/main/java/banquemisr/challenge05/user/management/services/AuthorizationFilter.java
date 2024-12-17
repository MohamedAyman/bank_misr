package banquemisr.challenge05.user.management.services;

import banquemisr.challenge05.user.management.entities.User;
import banquemisr.challenge05.user.management.repository.UserRepository;
import banquemisr.challenge05.user.management.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.*;

import static banquemisr.challenge05.user.management.constants.SecurityConstants.HEADER_STRING;
import static banquemisr.challenge05.user.management.constants.SecurityConstants.TOKEN_PREFIX;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
        this.jwtUtil = new JwtUtil();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String header = request.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace(TOKEN_PREFIX, "");

        try {
            String username = jwtUtil.extractUsername(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                Optional<User> userOptional = userRepository.findByUsername(username);

                if (userOptional.isPresent() && jwtUtil.validateToken(token, userOptional.get().getUsername())) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userOptional.get().getUsername(),
                            null,
                            List.of(new SimpleGrantedAuthority("ROLE_"+userOptional.get().getRole().getRoleName().toUpperCase()))
                    );
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid or expired token");
            return;
        }

        chain.doFilter(request, response);
    }
}
