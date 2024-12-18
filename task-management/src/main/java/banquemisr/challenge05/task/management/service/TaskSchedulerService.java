package banquemisr.challenge05.task.management.service;

import banquemisr.challenge05.task.management.DTO.MailNotification;
import banquemisr.challenge05.task.management.entities.TaskEntity;
import banquemisr.challenge05.task.management.mapper.MailNotificationMapper;
import banquemisr.challenge05.task.management.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskSchedulerService {

    private final TaskRepository taskRepository;
    private final MailNotificationMapper mailNotificationMapper;
    private final WebClient.Builder webClientBuilder;


    @Autowired
    public TaskSchedulerService(TaskRepository taskRepository, MailNotificationMapper mailNotificationMapper, WebClient.Builder webClientBuilder) {
        this.taskRepository = taskRepository;
        this.mailNotificationMapper = mailNotificationMapper;
        this.webClientBuilder = webClientBuilder;
    }

    // This method will run once a day at midnight (00:00)
    @Scheduled(cron = "0 0 0 * * ?")
    public void checkTasksDueTomorrow() {
        LocalDateTime today = LocalDateTime.now();

        List<TaskEntity> tasksDueTomorrow = taskRepository.findAll();

        for (TaskEntity task : tasksDueTomorrow) {
           if(task.getDueDate().isBefore(today) && !task.getStatus().getStatusName().equals("DONE")){
                // send mail to task due date passed and not finished
               sendMail(mailNotificationMapper.toMailNotification(task));
           }
        }
    }

    public void sendMail(MailNotification mailNotification) {
        webClientBuilder.baseUrl("http://localhost:8082")
                .build()
                .post()
                .uri("/send")
                .bodyValue(mailNotification)
                .retrieve();
    }
}
