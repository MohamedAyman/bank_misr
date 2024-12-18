package banquemisr.challenge05.task.management.mapper;

import banquemisr.challenge05.task.management.DTO.Status;
import banquemisr.challenge05.task.management.DTO.Task;
import banquemisr.challenge05.task.management.entities.StatusEntity;
import banquemisr.challenge05.task.management.entities.TaskEntity;
import banquemisr.challenge05.task.management.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class TaskMapper {
    public Task toDTO(TaskEntity taskEntity) {
        Task task = new Task();
        Status status = new Status(taskEntity.getStatus().getId(),taskEntity.getStatus().getStatusName());

        task.setId(taskEntity.getId());
        task.setTitle(taskEntity.getTitle());
        task.setDescription(taskEntity.getDescription());
        task.setStatus(status);
        task.setUser(UserMapper.toDTO(taskEntity.getUser()));
        task.setPriorityTask(taskEntity.getPriorityTask());
        task.setDueDate(taskEntity.getDueDate());
        task.setCreatedAt(taskEntity.getCreatedAt());
        task.setUpdatedAt(taskEntity.getUpdatedAt());
        return task;
    }

    public TaskEntity toEntity(Task task) {
        TaskEntity taskEntity = new TaskEntity();
        StatusEntity status = new StatusEntity();
        status.setId(task.getStatus().getId());
        status.setStatusName(task.getStatus().getStatusName());


        taskEntity.setId(task.getId());
        taskEntity.setTitle(task.getTitle());
        taskEntity.setDescription(task.getDescription());
        taskEntity.setStatus(status);
        taskEntity.setUser(UserMapper.toEntity(task.getUser()));
        taskEntity.setPriorityTask(task.getPriorityTask());
        taskEntity.setDueDate(task.getDueDate());
        taskEntity.setCreatedAt(task.getCreatedAt());
        taskEntity.setUpdatedAt(dateFormat(new Date()));
        return taskEntity;
    }

    public TaskEntity toEntity(Task task, TaskEntity taskEntity) {
        StatusEntity status = new StatusEntity();
        status.setId(task.getStatus().getId());
        status.setStatusName(task.getStatus().getStatusName());

        taskEntity.setId(task.getId());
        taskEntity.setTitle(task.getTitle() != null ? task.getTitle() : taskEntity.getTitle());
        taskEntity.setDescription(task.getDescription() != null ? task.getDescription() : taskEntity.getDescription());
        taskEntity.setStatus(status.getStatusName() != null ? status : taskEntity.getStatus());
        taskEntity.setUser(UserMapper.toEntity(task.getUser()));
        taskEntity.setPriorityTask(task.getPriorityTask());
        taskEntity.setDueDate(task.getDueDate() != null ? task.getCreatedAt() : taskEntity.getDueDate());
        taskEntity.setCreatedAt(task.getCreatedAt() != null ? task.getCreatedAt() : taskEntity.getCreatedAt());
        taskEntity.setUpdatedAt(dateFormat(new Date()));
        return taskEntity;
    }

    public LocalDateTime dateFormat(Date date) {
        // Define the formatter
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        // Format the date
        String formattedDate = formatter.format(date);
        return LocalDateTime.parse(formattedDate);
    }
}
