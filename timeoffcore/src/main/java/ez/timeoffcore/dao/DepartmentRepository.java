package ez.timeoffcore.dao;

import ez.timeoffcore.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Jpa Repository for Department entities
 *
 * @author Evgeniy Zagumennov
 */
@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, UUID> {}