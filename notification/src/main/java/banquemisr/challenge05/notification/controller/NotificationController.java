package banquemisr.challenge05.notification.controller;

import banquemisr.challenge05.notification.DTO.MailRequest;
import banquemisr.challenge05.notification.service.NotificationService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/send")
    public String sendEmail(@RequestBody MailRequest mailRequest) throws MessagingException {
        notificationService.sendEmail(mailRequest);
        return "Email sent successfully!";
    }
}
