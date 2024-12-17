package banquemisr.challenge05.user.management.utils;

import banquemisr.challenge05.user.management.entities.User;
import banquemisr.challenge05.user.management.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static banquemisr.challenge05.user.management.constants.SecurityConstants.getTokenSecret;

@Component
public class JwtUtil {

    @Autowired
    UserRepository userRepository;

    public String generateToken(String username, String role) {
       Optional<User> user =  userRepository.findByUsername(username);
        Map<String, Object> claims = new HashMap<>();
        claims.put("ROLE", "ROLE_"+role.toUpperCase());
        claims.put("UserName", username);
        claims.put("UserId", user.map(User::getId).orElse(null));
        return Jwts.builder()
                .setSubject(username)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2))
                .signWith(SignatureAlgorithm.HS256, getTokenSecret())
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(getTokenSecret()).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token, String username) {
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return Jwts.parser().setSigningKey(getTokenSecret()).parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
}
