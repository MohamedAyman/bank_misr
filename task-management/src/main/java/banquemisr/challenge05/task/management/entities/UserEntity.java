package banquemisr.challenge05.task.management.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
