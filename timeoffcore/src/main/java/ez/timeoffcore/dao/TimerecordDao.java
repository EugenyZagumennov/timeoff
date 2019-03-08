package ez.timeoffcore.dao;

import ez.timeoffcore.entities.Timerecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.UUID;

/**
 * Timerecord Data Access Object
 *
 * @author Evgeniy Zagumennov
 */
@Slf4j
@Repository("timerecordDao")
public class TimerecordDao {

    @PersistenceContext
    private EntityManager entityManager;

    public UUID save(Timerecord tr) {
        log.info("Create new timerecord = " + tr);
        entityManager.persist(tr);
        return tr.getUuid();
    }

    public Timerecord find(UUID uuid){
        log.info("Find timerecord with ID = " + uuid);
        return entityManager.find(Timerecord.class, uuid);
    }

    public List<Timerecord> findAll() {
        log.info("Find all timerecords");
        CriteriaQuery<Timerecord> criteria = entityManager.getCriteriaBuilder().createQuery(Timerecord.class);
        criteria.from(Timerecord.class);
        return entityManager.createQuery(criteria).getResultList();
    }

    public Timerecord merge(Timerecord tr){
        return entityManager.merge(tr);
    }

    public void remove(Timerecord tr){
        log.info("Remove timerecord = " + tr);
        if(!entityManager.contains(tr)){
            tr = entityManager.merge(tr);
        }
        tr.getUser().getTimerecords().remove(tr);
        tr.getTask().getTimerecords().remove(tr);
        entityManager.remove(tr);
    }
}
