package ez.timeoffcore.dao;

import ez.timeoffcore.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * User Data Access Object
 *
 * @author Evgeniy Zagumennov
 */
@Slf4j
@Repository("userDao")
public class UserDao  {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public UUID save(User entity) {
        log.info("User entity will be persisted", entity);
        entityManager.persist(entity);
        return entity.getUuid();
    }

    public List<User> getAll() {
        log.info("Select all users");
        return entityManager.createQuery("from users", User.class).getResultList();
    }

    public List<User> getAllWithTimerecords(){
        EntityGraph graph = entityManager.createEntityGraph("User.timerecords");
        graph.addAttributeNodes("timerecords");

        return entityManager.createQuery("from users", User.class)
                .setHint("javax.persistence.fetchgraph", graph)
                .getResultList();
    }
}
