package banquemisr.challenge05.notification.service;

import banquemisr.challenge05.notification.DTO.MailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    JavaMailSender mailSender;

    public String sendEmail(MailRequest mailRequest) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        String htmlContent = generateHtmlContent(mailRequest.getDescription(), mailRequest.getStatus(), mailRequest.getDueDate().toString());

        helper.setTo(mailRequest.getTo());
        helper.setSubject(mailRequest.getTitle());
        helper.setText(htmlContent, true);

        mailSender.send(mimeMessage);
        return "Message Sent";
    }


    private String generateHtmlContent(String description, String status, String dueDate) {
        return "<html>" +
                "<body>" +
                "<h2 style='color: #2E86C1;'>Task Notification</h2>" +
                "<p><strong>Description:</strong> " + description + "</p>" +
                "<p><strong>Status:</strong> " + status + "</p>" +
                "<p><strong>Due Date:</strong> " + dueDate + "</p>" +
                "<br>" +
                "<p style='font-size: 12px; color: gray;'>This is an automated message. Please do not reply.</p>" +
                "</body>" +
                "</html>";
    }
}
