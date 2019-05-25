package ez.timeoff.core.service;

import ez.timeoff.core.entities.DepartmentEntity;
import ez.timeoff.core.entities.enums.UserRoleEntity;
import ez.timeoff.core.repositories.DepartmentRepository;
import ez.timeoff.core.repositories.UserRepository;
import ez.timeoff.core.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
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

    @Autowired
    private DepartmentService departmentService;

    public UserEntity save(UserEntity user){
        return userRepository.save(user);
    }

    public UserEntity findById(UUID uuid){
        return userRepository.findById(uuid).orElse(null);
    }

    public List<UserEntity> findByNameLike(String name){
        return userRepository.findByNameContaining(name);
    }


    public List<UserEntity> findAll(){
        return userRepository.findAll();
    }

    public void delete(UserEntity user){
        userRepository.delete(user);
    }

    public void createNewUser(String login, String name, String password, String depUuid){
        DepartmentEntity department = departmentService.findById(UUID.fromString(depUuid));
        UserEntity user = new UserEntity(login, name, new Date(), password.getBytes(),
                department, UserRoleEntity.USER);
        save(user);
    }
}
