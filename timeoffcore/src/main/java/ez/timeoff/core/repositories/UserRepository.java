package ez.timeoff.core.repositories;

import ez.timeoff.core.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Jpa Repository for User entities
 *
 * @author Evgeniy Zagumennov
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {}
