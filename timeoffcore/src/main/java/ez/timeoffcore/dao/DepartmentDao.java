package ez.timeoffcore.dao;

import ez.timeoffcore.entities.Department;
import ez.timeoffcore.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

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
    public void save(Department entity) {
        log.info("Department entity will be persisted", entity);
        entityManager.persist(entity);
    }

    @Override
    public List<Department> getAll() {
        log.info("Select all departments");
        return entityManager.createQuery(
                    "from departments ds join ds.uuid", Department.class)
               .getResultList();
    }
}