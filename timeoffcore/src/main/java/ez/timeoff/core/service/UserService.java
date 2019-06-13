package ez.timeoff.core.service;

import ez.timeoff.core.dto.CreateUserDto;
import ez.timeoff.core.dto.UpdateUserDto;
import ez.timeoff.core.dto.mappers.CreateUserDtoMapper;
import ez.timeoff.core.dto.mappers.UpdateUserDtoMapper;
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
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CreateUserDtoMapper createMapper;

    @Autowired
    private UpdateUserDtoMapper updateMapper;

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

    public UserEntity createNewUser(CreateUserDto dto){
        UserEntity userEntity = createMapper.map(dto);
        save(userEntity);

        return userEntity;
    }

    public UserEntity updateUser(UpdateUserDto dto){
        UserEntity user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException(String.format("No user with id = %d", dto.getId())));

        user = updateMapper.update(dto, user);
        save(user);

        return user;
    }
}
