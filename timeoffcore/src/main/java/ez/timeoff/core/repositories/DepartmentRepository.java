package ez.timeoff.core.repositories;

import ez.timeoff.core.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Jpa Repository for Department entities
 *
 * @author Evgeniy Zagumennov
 */
@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, UUID> {

    List<DepartmentEntity> findByNameContaining(String name);
}