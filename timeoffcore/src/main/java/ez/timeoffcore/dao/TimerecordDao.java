package ez.timeoffcore.dao;

import ez.timeoffcore.entities.Timerecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Repository("timerecordDao")
public class TimerecordDao implements IDao<Timerecord> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Timerecord entity) {
        log.info("Timerecord entity will be persisted", entity);
        entityManager.persist(entity);
    }

    @Override
    public List<Timerecord> getAll() {
        log.info("Select all timerecords");
        return entityManager.createQuery("from timerecords", Timerecord.class).getResultList();
    }
}
