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

    public UUID createNew(User department){
        return userDao.save(department);
    }

    public User get(UUID uuid){
        return userDao.find(uuid);
    }

    public List<User> getAll(){
        return userDao.findAll();
    }

    public User merge(User department){
        return userDao.merge(department);
    }

    public void remove(User department){
        userDao.remove(department);
    }

    public List<User> getAllWithTimerecords(){
        return userDao.findAllWithTimerecords();
    }
}
