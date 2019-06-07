package ez.timeoff.core.service;

import ez.timeoff.core.dto.CreateUserDto;
import ez.timeoff.core.dto.mappers.CreateUserDtoMapper;
import ez.timeoff.core.repositories.UserRepository;
import ez.timeoff.core.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    @Autowired
    CreateUserDtoMapper mapper;

    public UserEntity save(UserEntity user){
        return userRepository.save(user);
    }

    public UserEntity findById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public List<UserEntity> findByFirstNameLike(String name){
        return userRepository.findAllByFirstNameContaining(name);
    }

    public UserEntity findByLogin(String login){
        return userRepository.findByLogin(login);
    }


    public List<UserEntity> findAll(){
        return userRepository.findAll();
    }

    public void delete(UserEntity user){
        userRepository.delete(user);
    }

    public UserEntity createNewUser(CreateUserDto userDto){
        UserEntity userEntity = mapper.map(userDto);
        save(userEntity);

        return userEntity;
    }
}
