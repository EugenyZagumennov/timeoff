package ez.timeoffcore.dao;

import ez.timeoffcore.entities.Task;
import ez.timeoffcore.entities.enums.TaskStatus;
import ez.timeoffcore.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(locations = "classpath:META-INF/spring-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @Test
    public void CRUD_Test() {
        //No tasks yet
        List<Task> tasks = taskService.getAll();
        assertEquals(0, tasks.size());

        //Create test task
        Task task = new Task("TestID", "Test task", TaskStatus.OPEN);
        UUID taskUuid = taskService.save(task);
        assertNotNull(taskUuid);

        //Fetch test task by UUID
        Task foundTask = taskService.get(taskUuid);
        assertEquals("TestID", foundTask.getStringId());

        //Update task and merge it to DB
        foundTask.setDescription("Updated task");
        taskService.merge(foundTask);

        //Check updated task description
        Task updatedTask = taskService.get(taskUuid);
        assertEquals("Updated task", updatedTask.getDescription());

        //Remove task from DB
        taskService.remove(updatedTask);
        tasks = taskService.getAll();
        assertEquals(0, tasks.size());
    }
}