package ez.timeoffcore.service;

import ez.timeoffcore.dao.UserRepository;
import ez.timeoffcore.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * UserEntity service class
 *
 * @author Evgeniy Zagumennov
 */
@Service("userService")
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity save(UserEntity user){
        return userRepository.save(user);
    }

    public UserEntity findById(UUID uuid){
        return userRepository.findById(uuid).orElse(null);
    }

    public List<UserEntity> getAll(){
        return userRepository.findAll();
    }

    public void delete(UserEntity user){
        userRepository.delete(user);
    }
}
