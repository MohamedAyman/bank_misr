package banquemisr.challenge05.task.management.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Long id;

    private String username;

    private String password;

    private Role role;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
