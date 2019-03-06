package ez.timeoffcore.service;

import ez.timeoffcore.dao.UserDao;
import ez.timeoffcore.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * User service class
 *
 * @author Evgeniy Zagumennov
 */
@Service("userService")
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    public UUID createNew(User user){
        return userDao.save(user);
    }

    public User get(UUID uuid){
        return userDao.find(uuid);
    }

    public List<User> getAll(){
        return userDao.findAll();
    }

    public User merge(User user){
        return userDao.merge(user);
    }

    public void remove(User user){
        userDao.remove(user);
    }

    public List<User> getAllWithTimerecords(){
        return userDao.findAllWithTimerecords();
    }
}
