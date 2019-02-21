package ez.timeoffcore.dao;

import ez.timeoffcore.entities.Department;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
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

    public UUID save(Department user) {
        log.info("Create new department = " + user);
        entityManager.persist(user);
        log.info("New department id = ", user.getUuid());
        return user.getUuid();
    }

    public Department find(UUID uuid){
        log.info("Find department with ID = " + uuid);
        return entityManager.find(Department.class, uuid);
    }

    public List<Department> findAll() {
        log.info("Find all departments");
        CriteriaQuery<Department> criteria = entityManager.getCriteriaBuilder().createQuery(Department.class);
        criteria.from(Department.class);
        return entityManager.createQuery(criteria).getResultList();
    }

    public Department merge(Department department){
        return entityManager.merge(department);
    }

    public void remove(Department department){
        log.info("Remove department = " + department);
        entityManager.remove(entityManager.contains(department) ? department : entityManager.merge(department));
    }
}