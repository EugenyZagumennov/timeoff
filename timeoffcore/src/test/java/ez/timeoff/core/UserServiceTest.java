package ez.timeoff.core;

import ez.timeoff.core.entities.UserEntity;
import ez.timeoff.core.entities.enums.UserRole;
import ez.timeoff.core.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void userCRUDTest() {
        //No users yet
        List<UserEntity> users = userService.findAll();
        assertEquals(0, users.size());

        //Create test user
        UserEntity user
                = new UserEntity("login", "FirstName",  "LastName", Instant.now(), "password".getBytes(), null, UserRole.USER);
        Long id = userService.save(user).getId();
        assertNotNull(id);

        //Fetch test user from DB
        UserEntity foundUser = userService.findById(id);
        assertEquals("FirstName", foundUser.getFirstName());

        //Rename test user and save to DB
        foundUser.setFirstName("Another Name");
        userService.save(foundUser);

        //Fetch renamed user from DB
        UserEntity anotherUser = userService.findById(id);
        assertNotNull(anotherUser);
        assertEquals("Another Name", anotherUser.getFirstName());

        //Remove user from DB
        userService.delete(anotherUser);
        UserEntity deletedUser = userService.findById(id);
        assertNull(deletedUser);
    }
}
