package ez.timeoffcore.dao;

import ez.timeoffcore.entities.Department;
import ez.timeoffcore.service.DepartmentService;
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

    @Test
    public void CRUD_Test() {
        List<Department> deps = departmentService.getAll();
        assertEquals(0, deps.size());

        Department newDepartment = new Department("TestDepartment", new Date());
        UUID departmentUuid = departmentService.createNew(newDepartment);
        assertNotNull(departmentUuid);

        Department foundDepartment = departmentService.get(departmentUuid);


    }
}
