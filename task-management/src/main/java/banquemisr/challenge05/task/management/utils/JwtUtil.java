package banquemisr.challenge05.task.management.utils;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.Date;

import static banquemisr.challenge05.task.management.constants.SecurityConstants.*;

@Component
public class JwtUtil {

    public Integer getUserId(HttpServletRequest request){
        return Integer.parseInt(extractByKey(request.getHeader(HEADER_STRING).replace(TOKEN_PREFIX,""), "UserId"));
    }
    public String extractByKey(String token, String claimKey) {
        return Jwts.parser().setSigningKey(getTokenSecret()).parseClaimsJws(token).getBody().get(claimKey).toString();
    }

    public boolean validateToken(String token) {
        return !Jwts.parser().setSigningKey(getTokenSecret()).parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }
}
