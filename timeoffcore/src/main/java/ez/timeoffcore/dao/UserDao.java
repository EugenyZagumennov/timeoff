package ez.timeoffcore.dao;

import ez.timeoffcore.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
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

    public UUID save(User user) {
        log.info("Create new user = " + user);
        entityManager.persist(user);
        return user.getUuid();
    }

    public User find(UUID uuid){
        log.info("Find user with ID = " + uuid);
        return entityManager.find(User.class, uuid);
    }

    public List<User> findAll() {
        log.info("Find all users");
        CriteriaQuery<User> criteria = entityManager.getCriteriaBuilder().createQuery(User.class);
        criteria.from(User.class);
        return entityManager.createQuery(criteria).getResultList();
    }

    public User merge(User user){
        return entityManager.merge(user);
    }

    public void remove(User user){
        log.info("Remove user = " + user);
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    public List<User> findAllWithTimerecords(){
        EntityGraph graph = entityManager.createEntityGraph("User.timerecords");
        graph.addAttributeNodes("timerecords");

        return entityManager.createQuery("from User", User.class)
                .setHint("javax.persistence.fetchgraph", graph)
                .getResultList();
    }
}
