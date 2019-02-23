package ez.timeoffcore.service;

import ez.timeoffcore.dao.TaskDao;
import ez.timeoffcore.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Task service class
 *
 * @author Evgeniy Zagumennov
 */
@Service("taskService")
@Transactional
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    public UUID createNew(Task task){
        return taskDao.save(task);
    }

    public Task get(UUID uuid){
        return taskDao.find(uuid);
    }

    public List<Task> getAll(){
        return taskDao.findAll();
    }

    public Task merge(Task task){
        return taskDao.merge(task);
    }

    public void remove(Task task){
        taskDao.remove(task);
    }
}
