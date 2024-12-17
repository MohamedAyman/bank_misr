package banquemisr.challenge05.task.management.security;

import banquemisr.challenge05.task.management.utils.JwtUtil;
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
import java.util.List;

import static banquemisr.challenge05.task.management.constants.SecurityConstants.HEADER_STRING;
import static banquemisr.challenge05.task.management.constants.SecurityConstants.TOKEN_PREFIX;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtUtil jwtUtil;

    public AuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
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

            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                if (jwtUtil.validateToken(token)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            jwtUtil.extractByKey(token, "UserName"),
                            null,
                            List.of(new SimpleGrantedAuthority(jwtUtil.extractByKey(token, "ROLE").toUpperCase()))
                    );
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        } catch (Exception e) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("Invalid or expired token");
            return;
        }

        chain.doFilter(request, response);
    }
}
