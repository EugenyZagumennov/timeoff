package ez.timeoffcore.dao;

import ez.timeoffcore.entities.Department;
import ez.timeoffcore.entities.User;
import ez.timeoffcore.entities.enums.UserRole;
import ez.timeoffcore.service.DepartmentService;
import ez.timeoffcore.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@ContextConfiguration(locations = "classpath:META-INF/spring-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @Test
    public void CRUD_Test() {
        //No departments yet
        List<Department> deps = departmentService.getAll();
        assertEquals(0, deps.size());

        //Create new 'TestDepartment'
        Department newDepartment = new Department("TestDepartment", new Date());
        UUID departmentUuid = departmentService.save(newDepartment);
        assertNotNull(departmentUuid);

        //Fetch TesrtDepartment from DB
        Department foundDepartment = departmentService.get(departmentUuid);
        assertEquals("TestDepartment", foundDepartment.getName());

        //Rename TestDepartment and save to DB
        foundDepartment.setName("AnotherDepartment");
        departmentService.merge(foundDepartment);

        //Fetch AnotherDepartment from DB
        Department anotherDepartment = departmentService.get(departmentUuid);
        assertEquals("AnotherDepartment", foundDepartment.getName());

        //Fetch departments with users
        User user = new User("login", "Name", new Date(), "qwerty".getBytes(), anotherDepartment, UserRole.USER);
        UUID userUuid = userService.save(user);
        assertNotNull(userUuid);

        deps = departmentService.getAllWithUsers();
        assertNotNull(deps);
        assertNotNull(deps.get(0));
        assertEquals(1, deps.get(0).getUsers().size());

        userService.remove(user);
        assertEquals(0, userService.getAll().size());
    }
}
