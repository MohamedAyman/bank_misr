package banquemisr.challenge05.task.management.service;

import banquemisr.challenge05.task.management.DTO.ValidateTokenRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class AuthenticationService {

    private static final String TOKEN_PREFIX = "Bearer ";

    @Autowired
    HttpServletRequest request;

    private final WebClient webClient;


    public AuthenticationService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8080").build();
    }


    public boolean validateToken() {
        String tokenWithoutBearer = request.getHeader("authorization").replace(TOKEN_PREFIX,"");
        try {
            webClient.post().uri("/validate_token")
                    .body(new ValidateTokenRequest(tokenWithoutBearer), ValidateTokenRequest.class)
                    .retrieve()
                    .toBodilessEntity()
                    .block();
            return true;
        } catch (WebClientResponseException e) {
            return e.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .anyMatch(a -> (a.startsWith("ADMIN")));
    }

    public  Long getCurrentUserId() {
        Claims body=this.getAuthData();
        return Long.parseLong(body.get("user_id").toString()) ;
    }
    private Claims getAuthData(){
        String tokenWithoutBearer = request.getHeader("authorization").replace(TOKEN_PREFIX,"");
        String tokenWithoutSignatureAndBearer = removeSignature(tokenWithoutBearer);
        return Jwts.parser()
                .parseClaimsJwt(tokenWithoutSignatureAndBearer)
                .getBody();
    }
    private String removeSignature(String jws) {
        int i = jws.lastIndexOf('.');
        return jws.substring(0, i + 1);
    }
}

