package ez.timeoffcore.dao;

import ez.timeoffcore.entities.Department;
import ez.timeoffcore.entities.User;
import ez.timeoffcore.service.DepartmentService;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(locations = "classpath:META-INF/spring-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    private Department department;

    @Before
    public void setUp() throws Exception {
        department = new Department("TestDepartment", new Date());
        departmentService.save(department);
        assertNotNull(department.getUuid());
    }

    @After
    public void tearDown() throws Exception {
        departmentService.remove(department);
        List<Department> deps = departmentService.getAll();
        assertEquals(0, deps.size());
    }

    @Test
    public void CRUD_Test() {
        //No users yet
        List<User> users = userService.getAll();
        assertEquals(0, users.size());

        //Create test user
        User user = new User("login", "User Name", new Date(), "password".getBytes(), department);
        UUID userUuid = userService.save(user);
        assertNotNull(userUuid);

        //Fetch test user from DB
        User foundUser = userService.get(userUuid);
        assertEquals("User Name", foundUser.getName());
        assertNotNull(foundUser.getDepartment());
        assertEquals("TestDepartment", foundUser.getDepartment().getName());

        //Rename test user and save to DB
        foundUser.setName("Another Name");
        userService.merge(foundUser);

        //Fetch renamed user from DB
        User anotherUser = userService.get(userUuid);
        assertNotNull(anotherUser);
        assertEquals("Another Name", anotherUser.getName());

        //Remove user from DB
        userService.remove(anotherUser);
        users = userService.getAll();
        assertEquals(0, users.size());
    }
}
