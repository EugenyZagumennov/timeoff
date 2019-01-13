package ez.timeoffcore.dao;

import ez.timeoffcore.entities.Department;
import ez.timeoffcore.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class DepartmentDao implements IDao<Department> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public UUID save(Department entity) {
        log.info("Department entity will be persisted", entity);
        entityManager.persist(entity);
        return entity.getUuid();
    }

    @Override
    public List<Department> getAll() {
        log.info("Select all departments");
        return entityManager.createQuery("select d from departments d left join fetch d.users").getResultList();
    }
}