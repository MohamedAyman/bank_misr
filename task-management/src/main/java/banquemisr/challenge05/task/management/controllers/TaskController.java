package banquemisr.challenge05.task.management.controllers;

import banquemisr.challenge05.task.management.DTO.Task;
import banquemisr.challenge05.task.management.entities.TaskEntity;
import banquemisr.challenge05.task.management.mapper.TaskMapper;
import banquemisr.challenge05.task.management.repository.StatusRepository;
import banquemisr.challenge05.task.management.repository.TaskRepository;
import banquemisr.challenge05.task.management.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static banquemisr.challenge05.task.management.constants.SecurityConstants.HEADER_STRING;
import static banquemisr.challenge05.task.management.constants.SecurityConstants.TOKEN_PREFIX;

@RestController
public class TaskController {

    HttpServletRequest request;
    private final TaskRepository taskRepository;
    private final StatusRepository statusRepository;
    private final TaskMapper taskMapper;
    private final JwtUtil jwtUtil;

    @Autowired
    public TaskController(TaskRepository taskRepository, StatusRepository statusRepository, TaskMapper taskMapper, HttpServletRequest request) {
        this.taskRepository = taskRepository;
        this.statusRepository = statusRepository;
        this.taskMapper = taskMapper;
        this.request = request;
        jwtUtil = new JwtUtil();
    }

    @PostMapping("/task")
    @PreAuthorize("hasRole('ADMIN')")
    public String createTask(@RequestBody Task task) {
        if (statusRepository.existsById(task.getStatus().getId())) {
            TaskEntity taskEntity = taskMapper.toEntity(task);
            taskEntity.setCreatedAt(taskMapper.dateFormat(new Date()));
            taskRepository.save(taskEntity);
            return "Task created successfully!";
        }
        return "Status ID not found!";
    }

    @PutMapping("/task")
    @PreAuthorize("hasRole('USER')")
    public String updateTask(@RequestBody Task task) {
        Integer userId = jwtUtil.getUserId(request);
        Optional<TaskEntity> taskEntity = taskRepository.findByIdAndUserId(task.getId(), userId);
        if (taskEntity.isPresent()) {
            taskRepository.save(taskMapper.toEntity(task, taskEntity.get()));
            return "Task updated successfully!";
        }
        return "Task ID not found!";
    }

    @GetMapping("/task/{task_id}")
    @PreAuthorize("hasRole('USER')")
    public Task readTask(@PathVariable("task_id") Integer taskId) {
        Integer userId = jwtUtil.getUserId(request);
        return taskMapper.toDTO(taskRepository.findByIdAndUserId(taskId, userId).orElse(null));
    }

    @DeleteMapping("/task/{task_id}")
    @PreAuthorize("hasRole('USER')")
    public boolean deleteTask(@PathVariable("task_id") Integer taskId) {
        Integer userId = jwtUtil.getUserId(request);
        if (taskRepository.findByIdAndUserId(taskId, userId).isPresent()) {
            taskRepository.deleteById(taskId);
            return true;
        }
        return false;
    }

    @GetMapping("/tasks")
    @PreAuthorize("hasRole('USER')")
    public List<Task> readTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Integer userId = jwtUtil.getUserId(request);
        return taskRepository.findByUserId(userId, pageable).stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());

    }
}

