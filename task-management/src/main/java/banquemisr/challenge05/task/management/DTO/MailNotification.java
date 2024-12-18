package banquemisr.challenge05.task.management.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MailNotification {
    private String to;
    private String title;
    private String description;
    private String status;
    private LocalDateTime dueDate;
}
