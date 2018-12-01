package ez.timeoffcore.dao;

import ez.timeoffcore.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * User Data Access Object
 *
 * @author Evgeniy Zagumennov
 */
@Slf4j
@Repository("userDao")
public class UserDao implements IDao<User> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(User entity) {
        log.info("User entity will be persisted", entity);
        entityManager.persist(entity);
    }

    @Override
    public List<User> getAll() {
        log.info("Select all users");
        return entityManager.createQuery("from users", User.class).getResultList();
    }
}
