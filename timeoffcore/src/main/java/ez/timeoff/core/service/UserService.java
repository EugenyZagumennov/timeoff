package ez.timeoff.core.service;

import ez.timeoff.core.dto.CreateUserDto;
import ez.timeoff.core.dto.mappers.CreateUserDtoMapper;
import ez.timeoff.core.entities.DepartmentEntity;
import ez.timeoff.core.repositories.UserRepository;
import ez.timeoff.core.entities.UserEntity;
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

    public void createNewUser(CreateUserDto userDto){
        DepartmentEntity department = departmentService.findById(UUID.fromString(userDto.getDepUuid()));
        UserEntity userEntity = CreateUserDtoMapper.INSTANCE.map(userDto);
        userEntity.setDepartment(department);
        save(userEntity);
    }
}
