package banquemisr.challenge05.task.management.repository;

import banquemisr.challenge05.task.management.entities.StatusEntity;
import banquemisr.challenge05.task.management.entities.TaskEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    public void setUp() {
        taskRepository.deleteAll();
        // Prepare test data
        TaskEntity task1 = new TaskEntity();
        task1.setUserId(1);
        task1.setTitle("Task 1");
        task1.setDescription("Description for Task 1");
        StatusEntity status = new StatusEntity();
        status.setStatusName("TODO");
        status.setId(1);
        task1.setStatus(status);
        taskRepository.save(task1);

        TaskEntity task2 = new TaskEntity();
        task2.setUserId(1);
        task2.setTitle("Task 2");
        task2.setDescription("Description for Task 2");
        task2.setStatus(status);
        taskRepository.save(task2);

        TaskEntity task3 = new TaskEntity();
        task3.setUserId(3);
        task3.setTitle("Task 3");
        task3.setDescription("Description for Task 3");
        task3.setStatus(status);
        taskRepository.save(task3);
    }

    @Test
    public void testFindByUserId() {
        // Given
        Pageable pageable = PageRequest.of(0, 2);

        // When
        List<TaskEntity> tasks = taskRepository.findByUserId(1, pageable);
        // Then
        assertEquals(2, tasks.size());
        assertEquals("Task 1", tasks.get(0).getTitle());
        assertEquals("Task 2", tasks.get(1).getTitle());
    }

    @Test
    public void testFindByIdAndUserId() {
        // Given
        TaskEntity task = new TaskEntity();
        task.setUserId(3);
        task.setTitle("Task 4");
        task.setDescription("Description for Task 4");
        StatusEntity status = new StatusEntity();
        status.setStatusName("TODO");
        status.setId(1);
        task.setStatus(status);
        TaskEntity savedTask = taskRepository.save(task);

        // When
        Optional<TaskEntity> retrievedTask = taskRepository.findByIdAndUserId(savedTask.getId(), 3);

        // Then
        assertTrue(retrievedTask.isPresent());
        assertEquals("Task 4", retrievedTask.get().getTitle());
        assertEquals("Description for Task 4", retrievedTask.get().getDescription());
    }

    @Test
    public void testFindByIdAndUserId_NotFound() {
        // When
        Optional<TaskEntity> retrievedTask = taskRepository.findByIdAndUserId(999, 1);

        // Then
        assertFalse(retrievedTask.isPresent());
    }
}
