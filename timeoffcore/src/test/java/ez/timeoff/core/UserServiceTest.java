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

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        UserEntity user = new UserEntity("login", "UserEntity Name", new Date(), "password".getBytes(), null, UserRole.USER);
        UUID uuid = userService.save(user).getUuid();
        assertNotNull(uuid);

        //Fetch test user from DB
        UserEntity foundUser = userService.findById(uuid);
        assertEquals("UserEntity Name", foundUser.getName());

        //Rename test user and save to DB
        foundUser.setName("Another Name");
        userService.save(foundUser);

        //Fetch renamed user from DB
        UserEntity anotherUser = userService.findById(uuid);
        assertNotNull(anotherUser);
        assertEquals("Another Name", anotherUser.getName());

        //Remove user from DB
        userService.delete(anotherUser);
        UserEntity deletedUser = userService.findById(uuid);
        assertNull(deletedUser);
    }
}
