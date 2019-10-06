package ez.timeoff.core.service;

import ez.timeoff.core.dto.CreateUserDto;
import ez.timeoff.core.dto.UpdateUserDto;
import ez.timeoff.core.dto.mappers.CreateUserDtoMapper;
import ez.timeoff.core.dto.mappers.UpdateUserDtoMapper;
import ez.timeoff.core.repositories.UserRepository;
import ez.timeoff.core.entities.UserEntity;
import ez.timeoff.core.service.storage.FileStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

/**
 * UserEntity service class
 *
 * @author Evgeniy Zagumennov
 */
@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private CreateUserDtoMapper createMapper;

    @Autowired
    private UpdateUserDtoMapper updateMapper;

    @Autowired
    private FileStorageService fileStorageService;

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

    public UserEntity createNewUser(CreateUserDto dto, MultipartFile file){
        fileStorageService.store(file,dto.getLogin());

        UserEntity userEntity = createMapper.map(dto);
        save(userEntity);

        return userEntity;
    }

    public UserEntity updateUser(UpdateUserDto dto, MultipartFile file){
        if(!file.isEmpty()){
            fileStorageService.store(file, dto.getLogin());
        }

        UserEntity user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException(String.format("No user with id = %d", dto.getId())));

        user = updateMapper.update(dto, user);
        save(user);

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userRepository.findByLogin(username);
        if(userDetails == null){
            throw new UsernameNotFoundException(String.format("Пользователь '%s' не найден!", username));
        }
        return userDetails;
    }
}
