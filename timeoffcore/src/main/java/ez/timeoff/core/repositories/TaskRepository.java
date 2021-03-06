package ez.timeoff.core.repositories;

import ez.timeoff.core.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Jpa Repository for Timerecord entities
 *
 * @author Evgeniy Zagumennov
 */
@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {}
