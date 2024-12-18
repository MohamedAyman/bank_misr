package banquemisr.challenge05.task.management.mapper;

import banquemisr.challenge05.task.management.DTO.MailNotification;
import banquemisr.challenge05.task.management.entities.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class MailNotificationMapper {

    public MailNotification toMailNotification(TaskEntity taskEntity) {
        MailNotification mailNotification = new MailNotification();
        mailNotification.setTitle(taskEntity.getTitle());
        mailNotification.setDescription(taskEntity.getDescription());
        mailNotification.setStatus(taskEntity.getStatus().getStatusName());
        mailNotification.setTo(taskEntity.getUser().getEmail());
        mailNotification.setDueDate(taskEntity.getDueDate());
        return mailNotification;
    }
}
