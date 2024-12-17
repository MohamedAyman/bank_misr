package banquemisr.challenge05.user.management.controllers;

import banquemisr.challenge05.user.management.DTO.AuthRequest;
import banquemisr.challenge05.user.management.DTO.TokenRequest;
import banquemisr.challenge05.user.management.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(o -> o.getAuthority().contains("ADMIN"));
        return jwtUtil.generateToken(authentication.getName(), isAdmin ? "ADMIN" : "USER");
    }

    @PostMapping("/validate_token")
    public ResponseEntity validateToken(@RequestBody TokenRequest tokenRequest) {
       boolean isTokenExpired = jwtUtil.isTokenExpired(tokenRequest.getToken());
       if(!isTokenExpired) {
           return new ResponseEntity<>(HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}

