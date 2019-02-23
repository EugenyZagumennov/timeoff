package ez.timeoffcore.dao;

import ez.timeoffcore.entities.Department;
import ez.timeoffcore.entities.Task;
import ez.timeoffcore.entities.Timerecord;
import ez.timeoffcore.entities.User;
import ez.timeoffcore.service.DepartmentService;
import ez.timeoffcore.service.TaskService;
import ez.timeoffcore.service.TimerecordService;
import ez.timeoffcore.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertArrayEquals;

@ContextConfiguration(locations = "classpath:META-INF/spring-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DaoTests {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TimerecordService timerecordService;

    @Test
    public void daoTests() throws NoSuchAlgorithmException {
        List<Department> deps = departmentService.getAll();
        assertEquals(0, deps.size());

        Department newDepartment = new Department("TestDepartment", new Date());
        UUID departmentUuid = departmentService.createNew(newDepartment);
        assertNotNull(departmentUuid);
        deps = departmentService.getAll();
        assertEquals(1, deps.size());
        assertEquals("TestDepartment", deps.get(0).getName());

        List<User> users = userService.getAll();
        assertEquals(0, users.size());

        User newUser = new User("TestLogin", "Test Test", new Date(),
                MessageDigest.getInstance("MD5").digest("qwerty".getBytes()),
                newDepartment);

        UUID userUuid = userService.createNew(newUser);
        assertNotNull(userUuid);
        users = userService.getAll();
        assertEquals(1, users.size());
        assertEquals(users.get(0).getName(), "Test Test");
        assertEquals(users.get(0).getLogin(), "TestLogin");
        assertEquals(users.get(0).getDepartment().getUuid(), newDepartment.getUuid());
        assertArrayEquals(users.get(0).getPassword(), MessageDigest.getInstance("MD5").digest("qwerty".getBytes()));

        users = userService.getAllWithTimerecords();
        assertEquals(1, users.size());
        assertEquals(users.get(0).getName(), "Test Test");
        assertEquals(users.get(0).getLogin(), "TestLogin");
        //assertEquals(1, users.get(0).getTimerecords().size());

        List<Task> tasks = taskService.getAll();
        assertEquals(0, tasks.size());
        List<Timerecord> trs = timerecordService.getAll();
        assertEquals(0, trs.size());

        Task newTask = new Task("T-1", "Test task");
        Timerecord newTimerecord = new Timerecord((new Date()).getTime(), newUser, 4.0, newTask);
        UUID taskUuid = taskService.createNew(newTask);
        assertNotNull(taskUuid);
        UUID trUuid = timerecordService.createNew(newTimerecord);
        assertNotNull(trUuid);

        tasks = taskService.getAll();
        assertEquals(1, tasks.size());
        assertEquals("T-1", newTask.getStringId());
        assertEquals("Test task", newTask.getDescription());

        trs = timerecordService.getAll();
        assertEquals(1, trs.size());
        assertEquals(4.0, trs.get(0).getHours());
        assertEquals(trs.get(0).getUser().getUuid(), newUser.getUuid());
        assertEquals(trs.get(0).getTask().getUuid(), newTask.getUuid());

        tasks = taskService.getAll();
        assertEquals(1, tasks.size());
        assertEquals("T-1", tasks.get(0).getStringId());
        assertEquals("Test task", tasks.get(0).getDescription());
        assertEquals(1, tasks.get(0).getTimerecords().size());
        assertEquals(4, tasks.get(0).getTimerecords().get(0).getHours());
    }
}
