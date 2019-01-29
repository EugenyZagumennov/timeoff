package ez.timeoffcore.dao;

import ez.timeoffcore.entities.Department;
import ez.timeoffcore.entities.Task;
import ez.timeoffcore.entities.Timerecord;
import ez.timeoffcore.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static junit.framework.TestCase.*;

@ContextConfiguration(locations = "classpath:META-INF/spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DaoTests {

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private TimerecordDao timerecordDao;

    @Test
    @Transactional
    public void daoTests() throws NoSuchAlgorithmException {
        List<Department> deps = departmentDao.getAll();
        assertEquals(0, deps.size());

        Department newDep = new Department("TestDepartment", new Date());
        UUID departmentUuid = departmentDao.save(newDep);
        assertNotNull(departmentUuid);
        deps = departmentDao.getAll();
        assertEquals(1, deps.size());
        assertEquals("TestDepartment", deps.get(0).getName());

        List<User> users = userDao.getAll();
        assertEquals(0, users.size());

        User newUser = new User("TestLogin", "Test Test", new Date(),
                MessageDigest.getInstance("MD5").digest("qwerty".getBytes()),
                newDep);

        UUID userUuid = userDao.save(newUser);
        assertNotNull(userUuid);
        users = userDao.getAll();
        assertEquals(1, users.size());
        assertEquals(users.get(0).getName(), "Test Test");
        assertEquals(users.get(0).getLogin(), "TestLogin");
        assertTrue(Arrays.equals(users.get(0).getPassword(), MessageDigest.getInstance("MD5").digest("qwerty".getBytes())));

        users = userDao.getAllWithTimerecords();
        assertEquals(1, users.size());
        assertEquals(users.get(0).getName(), "Test Test");
        assertEquals(users.get(0).getLogin(), "TestLogin");
        assertEquals(1, users.get(0).getTimerecords().size());

        List<Task> tasks = taskDao.getAll();
        assertEquals(0, tasks.size());
        List<Timerecord> trs = timerecordDao.getAll();
        assertEquals(0, trs.size());

        Task task = new Task("T-1", "Test task");
        Timerecord tr = new Timerecord((new Date()).getTime(), newUser, 4.0, task);
        UUID taskUuid = taskDao.save(task);
        assertNotNull(taskUuid);
        UUID trUuid = timerecordDao.save(tr);
        assertNotNull(trUuid);

        tasks = taskDao.getAll();
        assertEquals(1, tasks.size());
        assertEquals("T-1", task.getStringId());
        assertEquals("Test task", task.getDescription());

        trs = timerecordDao.getAll();
        assertEquals(1, trs.size());
        assertEquals(4.0, trs.get(0).getHours());

        tasks = taskDao.getAllWithTimerecords();
        assertEquals(1, tasks.size());
        assertEquals("T-1", tasks.get(0).getStringId());
        assertEquals("Test task", tasks.get(0).getDescription());
        assertEquals(1, tasks.get(0).getTimerecords().size());
        assertEquals(4, tasks.get(0).getTimerecords().get(0).getHours());
    }
}
