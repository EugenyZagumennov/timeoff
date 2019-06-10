package ez.timeoff.core.service;

import ez.timeoff.core.repositories.TaskRepository;
import ez.timeoff.core.entities.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * TaskEntity service class
 *
 * @author Evgeniy Zagumennov
 */
@Service
@Transactional
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskEntity save(TaskEntity task){
        return taskRepository.save(task);
    }

    public TaskEntity findById(Long id){
        return taskRepository.findById(id).orElse(null);
    }

    public List<TaskEntity> findAll(){
        return taskRepository.findAll();
    }

    public void delete(TaskEntity task){
        taskRepository.delete(task);
    }
}
