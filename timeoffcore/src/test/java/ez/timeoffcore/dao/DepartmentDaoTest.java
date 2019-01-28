package ez.timeoffcore.dao;

import ez.timeoffcore.entities.Department;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static junit.framework.TestCase.*;

@ContextConfiguration(locations = "classpath:META-INF/spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DepartmentDaoTest {

    @Autowired
    private DepartmentDao departmentDao;

    @Test
    @Transactional
    @Rollback(true)
    public void saveAndGetTest() {
        Department department = new Department("TestDepartment", new Date());
        UUID newUUID = departmentDao.save(department);
        assertNotNull(newUUID);

        List<Department> departments = departmentDao.getAll();
        assertNotNull(departments);
        assertEquals(departments.size(), 1);
        assertEquals(departments.get(0).getName(), "TestDepartment");
    }
}
