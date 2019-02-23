package ez.timeoffcore.dao;

import ez.timeoffcore.entities.Task;
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
 * Task Data Access Object
 *
 * @author Evgeniy Zagumennov
 */
@Slf4j
@Repository("taskDao")
public class TaskDao {

    @PersistenceContext
    private EntityManager entityManager;

    public UUID save(Task task) {
        log.info("Create new task = " + task);
        entityManager.persist(task);
        return task.getUuid();
    }

    public Task find(UUID uuid){
        log.info("Find task with ID = " + uuid);
        return entityManager.find(Task.class, uuid);
    }

    public List<Task> findAll() {
        log.info("Find all tasks");
        CriteriaQuery<Task> criteria = entityManager.getCriteriaBuilder().createQuery(Task.class);
        criteria.from(Task.class);
        return entityManager.createQuery(criteria).getResultList();
    }

    public Task merge(Task task){
        return entityManager.merge(task);
    }

    public void remove(Task task){
        log.info("Remove task = " + task);
        entityManager.remove(entityManager.contains(task) ? task : entityManager.merge(task));
    }

    public List<Task> findAllWithTimerecords(){
         EntityGraph graph = entityManager.createEntityGraph("Task.timerecords");

        return entityManager.createQuery("from Task", Task.class)
                .setHint("javax.persistence.fetchgraph", graph)
                .getResultList();
    }
}
