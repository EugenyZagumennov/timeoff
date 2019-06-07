package ez.timeoff.core.repositories;

import ez.timeoff.core.entities.TimerecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Jpa Repository for Timerecord entities
 *
 * @author Evgeniy Zagumennov
 */
@Repository
public interface TimerecordRepository extends JpaRepository<TimerecordEntity, Long> { }
