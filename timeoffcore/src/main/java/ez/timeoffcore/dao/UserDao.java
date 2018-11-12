package ez.timeoffcore.dao;

import ez.timeoffcore.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * User Data Access Object
 *
 * @author Evgeniy Zagumennov
 */
@Repository("daoImpl")
public class UserDao implements IDao<User> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(User entity) {
        em.persist(entity);
    }

    @Override
    public List<User> getAll() {
        return em.createQuery("from user", User.class).getResultList();
    }
}
