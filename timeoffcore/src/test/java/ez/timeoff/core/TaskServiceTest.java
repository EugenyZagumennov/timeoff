package ez.timeoff.core;

import ez.timeoff.core.entities.TaskEntity;
import ez.timeoff.core.entities.enums.TaskStatus;
import ez.timeoff.core.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @Test
    public void taskCRUDTest(){
        //No tasks yet
        List<TaskEntity> deps = taskService.findAll();
        assertEquals(0, deps.size());

        //Create new 'TestTask'
        TaskEntity newTask = new TaskEntity("T1", "Test Task", TaskStatus.OPEN);
        Long id = taskService.save(newTask).getId();
        assertNotNull(newTask);

        //Fetch TestTask from DB
        TaskEntity foundTask = taskService.findById(id);
        assertEquals("Test Task", foundTask.getDescription());

        //Rename TestTask and save to DB
        foundTask.setDescription("Another Task");
        taskService.save(foundTask);

        //Fetch AnotherTask from DB
        TaskEntity anotherTask = taskService.findById(id);
        assertEquals("Another Task", anotherTask.getDescription());

        //Remove task from DB
        taskService.delete(anotherTask);
        TaskEntity deletedTask = taskService.findById(id);
        assertNull(deletedTask);
    }
}
