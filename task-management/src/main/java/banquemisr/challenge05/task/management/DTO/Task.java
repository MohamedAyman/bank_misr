package banquemisr.challenge05.task.management.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private int id;

    @NotNull
    @Size(min = 5, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @NotNull
    @Size(min = 5, max = 500, message = "Description must be between 5 and 500 characters")
    private String description;

    @NotNull
    private Status status;

    @NotNull
    private User user;

    @NotNull
    private Boolean priorityTask;

    @Future(message = "Due date must be in the future")
    private LocalDateTime dueDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
