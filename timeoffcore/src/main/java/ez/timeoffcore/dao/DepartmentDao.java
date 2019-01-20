package ez.timeoffcore.dao;

import ez.timeoffcore.entities.Department;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Department Data Access Object
 *
 * @author Evgeniy Zagumennov
 */
@Slf4j
@Repository("departmentDao")
public class DepartmentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public UUID save(Department entity) {
        log.info("Department entity will be persisted", entity);
        entityManager.persist(entity);
        return entity.getUuid();
    }

    public List<Department> getAll() {
        log.info("Select all departments");
        CriteriaQuery<Department> criteria = entityManager.getCriteriaBuilder().createQuery(Department.class);
        criteria.from(Department.class);
        return entityManager.createQuery(criteria).getResultList();
    }
}