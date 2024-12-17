package banquemisr.challenge05.user.management.DTO;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
