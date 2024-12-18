package banquemisr.challenge05.task.management.utils;

import banquemisr.challenge05.task.management.entities.TaskEntity;
import banquemisr.challenge05.task.management.entities.UserEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TaskSpecification {

    public static Specification<TaskEntity> hasUser(UserEntity user) {
        return (root, query, builder) -> builder.equal(root.get("user"), user);
    }

    public static Specification<TaskEntity> hasTitle(String title) {
        return (root, query, builder) -> builder.like(root.get("title"), "%" + title + "%");
    }

    public static Specification<TaskEntity> hasDescription(String description) {
        return (root, query, builder) -> builder.like(root.get("description"), "%" + description + "%");
    }

    public static Specification<TaskEntity> hasDueDate(LocalDate dueDate) {
        return (root, query, builder) -> builder.equal(root.get("dueDate"), dueDate);
    }

    public static Specification<TaskEntity> hasStatus(String status) {
        return (root, query, builder) -> builder.equal(root.get("status"), status);
    }
}
