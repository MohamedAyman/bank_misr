package banquemisr.challenge05.notification.service;
import banquemisr.challenge05.notification.DTO.MailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NotificationServiceTest {

    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private JavaMailSender mailSender;

    @Test
    public void testSendEmail() throws MessagingException {
        MailRequest mailRequest = new MailRequest();
        mailRequest.setTo("test@example.com");
        mailRequest.setTitle("Test Email");
        mailRequest.setDescription("Test Description");
        mailRequest.setStatus("Pending");
        mailRequest.setDueDate(java.time.LocalDateTime.of(2024, 12, 20, 1, 30));

        MimeMessage mimeMessage = Mockito.mock(MimeMessage.class);
        Mockito.when(mailSender.createMimeMessage()).thenReturn(mimeMessage);


        String response = notificationService.sendEmail(mailRequest);


        verify(mailSender).createMimeMessage();
        verify(mailSender).send(mimeMessage);
        assertEquals("Message Sent", response);
    }
}
