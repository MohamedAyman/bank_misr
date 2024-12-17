package banquemisr.challenge05.task.management.repository;

import banquemisr.challenge05.task.management.entities.TaskEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
    @Query("SELECT t FROM TaskEntity t WHERE t.userId = :userId")
    List<TaskEntity> findByUserId(@Param("userId") Integer userId, Pageable pageable);

    Optional<TaskEntity> findByIdAndUserId(Integer id, Integer userId);
}
