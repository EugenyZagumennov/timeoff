package ez.timeoffcore.dao;

import ez.timeoffcore.entities.Timerecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Slf4j
@Repository("timerecordDao")
public class TimerecordDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public UUID save(Timerecord entity) {
        log.info("Timerecord entity will be persisted", entity);
        entityManager.persist(entity);
        return entity.getUuid();
    }

    public List<Timerecord> getAll() {
        log.info("Select all timerecords");
        return entityManager.createQuery("from Timerecord", Timerecord.class).getResultList();
    }
}
