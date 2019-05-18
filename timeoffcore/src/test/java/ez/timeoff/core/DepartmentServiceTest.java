package ez.timeoff.core;

import ez.timeoff.core.entities.DepartmentEntity;
import ez.timeoff.core.service.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void departmentCRUDTest() {
        //No departments yet
        List<DepartmentEntity> deps = departmentService.findAll();
        assertEquals(0, deps.size());

        //Create new 'TestDepartment'
        DepartmentEntity newDepartment = new DepartmentEntity("TestDepartment", new Date());
        UUID uuid = departmentService.save(newDepartment).getUuid();
        assertNotNull(newDepartment);

        //Fetch TestDepartment from DB
        DepartmentEntity foundDepartment = departmentService.findById(uuid);
        assertEquals("TestDepartment", foundDepartment.getName());

        //Rename TestDepartment and save to DB
        foundDepartment.setName("AnotherDepartment");
        departmentService.save(foundDepartment);

        //Fetch AnotherDepartment from DB
        DepartmentEntity anotherDepartment = departmentService.findById(uuid);
        assertEquals("AnotherDepartment", anotherDepartment.getName());

        //Remove department from DB
        departmentService.delete(anotherDepartment);
        DepartmentEntity deletedDepartment = departmentService.findById(uuid);
        assertNull(deletedDepartment);
    }
}
