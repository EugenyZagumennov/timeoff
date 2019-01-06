package ez.timeoffcore.dao;

import ez.timeoffcore.entities.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Repository("taskDao")
public class TaskDao implements IDao<Task> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Task entity) {
        log.info("Task entity will be persisted", entity);
        entityManager.persist(entity);
    }

    @Override
    public List<Task> getAll() {
        log.info("Select all tasks");
        return entityManager.createQuery("from tasks", Task.class).getResultList();
    }
}
