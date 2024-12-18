package banquemisr.challenge05.notification.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MailRequest {
    private String to;
    private String title;
    private String description;
    private String status;
    private LocalDateTime dueDate;
}
