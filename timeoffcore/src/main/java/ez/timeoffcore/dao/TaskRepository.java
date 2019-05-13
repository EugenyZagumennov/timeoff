package ez.timeoffcore.dao;

import ez.timeoffcore.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Jpa Repository for Timerecord entities
 *
 * @author Evgeniy Zagumennov
 */
@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {}
