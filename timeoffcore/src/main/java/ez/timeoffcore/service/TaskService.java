package ez.timeoffcore.service;

import ez.timeoffcore.dao.TaskRepository;
import ez.timeoffcore.entities.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * TaskEntity service class
 *
 * @author Evgeniy Zagumennov
 */
@Service("taskService")
@Transactional
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskEntity save(TaskEntity task){
        return taskRepository.save(task);
    }

    public TaskEntity get(UUID uuid){
        return taskRepository.findById(uuid).orElse(null);
    }

    public List<TaskEntity> getAll(){
        return taskRepository.findAll();
    }

    public void delete(TaskEntity task){
        taskRepository.delete(task);
    }
}
