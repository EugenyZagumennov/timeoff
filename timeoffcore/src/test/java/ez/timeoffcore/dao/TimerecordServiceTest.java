package ez.timeoffcore.dao;

import ez.timeoffcore.entities.Department;
import ez.timeoffcore.entities.Task;
import ez.timeoffcore.entities.Timerecord;
import ez.timeoffcore.entities.User;
import ez.timeoffcore.service.DepartmentService;
import ez.timeoffcore.service.TaskService;
import ez.timeoffcore.service.TimerecordService;
import ez.timeoffcore.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@ContextConfiguration(locations = "classpath:META-INF/spring-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TimerecordServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private TimerecordService timerecordService;

    private Department department;
    private User user;
    private Task task;

    @Before
    public void setUp() throws Exception {
        department = new Department("TestDepartment", new Date());
        UUID depUuid = departmentService.createNew(department);
        assertNotNull(depUuid);

        user = new User("login", "Name", new Date(), "qwerty".getBytes(), department);
        UUID userUuid = userService.createNew(user);
        assertNotNull(userUuid);

        task = new Task("TestID", "Test task");
        UUID taskUuid = taskService.createNew(task);
        assertNotNull(taskUuid);
    }

    @After
    public void tearDown() throws Exception {
        taskService.remove(task);
        assertEquals(0, taskService.getAll().size());
        userService.remove(user);
        assertEquals(0, userService.getAll().size());
        departmentService.remove(department);
        assertEquals(0, departmentService.getAll().size());
    }

    @Test
    public void CRUD_Test() {
        List<Timerecord> trs = timerecordService.getAll();
        assertEquals(0, trs.size());

        Timerecord tr = new Timerecord(999L, user, 8, task);
        UUID trUuid = timerecordService.createNew(tr);

        Timerecord foundTr = timerecordService.get(trUuid);
        assertEquals(999, tr.getTimestamp());
        assertEquals(user.getLogin(), foundTr.getUser().getLogin());
        assertEquals(task.getStringId(), foundTr.getTask().getStringId());

        foundTr.setHours(4);
        timerecordService.merge(foundTr);

        Timerecord updatedTr = timerecordService.get(trUuid);
        assertEquals(4, updatedTr.getHours(), 0.01);

        timerecordService.remove(updatedTr);
        trs = timerecordService.getAll();
        assertEquals(0, trs.size());
    }
}