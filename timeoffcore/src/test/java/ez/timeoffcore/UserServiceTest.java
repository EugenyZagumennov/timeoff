package ez.timeoffcore;

import ez.timeoffcore.entities.UserEntity;
import ez.timeoffcore.entities.enums.UserRoleEntity;
import ez.timeoffcore.service.UserService;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void CRUD_Test() {
        //No users yet
        List<UserEntity> users = userService.getAll();
        assertEquals(0, users.size());

        //Create test user
        UserEntity user = new UserEntity("login", "UserEntity Name", new Date(), "password".getBytes(), null, UserRoleEntity.USER);
        user = userService.save(user);
        assertNotNull(user);
        assertNotNull(user.getUuid());

        //Fetch test user from DB
        UserEntity foundUser = userService.findById(user.getUuid());
        assertEquals("UserEntity Name", foundUser.getName());

        //Rename test user and save to DB
        foundUser.setName("Another Name");
        userService.save(foundUser);

        //Fetch renamed user from DB
        UserEntity anotherUser = userService.findById(foundUser.getUuid());
        assertNotNull(anotherUser);
        assertEquals("Another Name", anotherUser.getName());

        //Remove user from DB
        userService.delete(anotherUser);
        UserEntity deletedUser = userService.findById(anotherUser.getUuid());
        assertNull(deletedUser);
    }
}
