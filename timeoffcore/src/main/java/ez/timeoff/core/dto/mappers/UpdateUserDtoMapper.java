package ez.timeoff.core.dto.mappers;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import ez.timeoff.core.dto.UpdateUserDto;
import ez.timeoff.core.entities.DepartmentEntity;
import ez.timeoff.core.entities.UserEntity;
import ez.timeoff.core.entities.enums.UserRole;
import ez.timeoff.core.entities.enums.UserStatus;
import ez.timeoff.core.service.DepartmentService;

@Mapper(componentModel = "spring")
public abstract class UpdateUserDtoMapper{

    @Autowired
    private DepartmentService departmentService;

    public UserEntity update(UpdateUserDto src, UserEntity target){
        if(src.getLogin() != null && !src.getLogin().isEmpty()){
            target.setLogin(src.getLogin());
        }
        if(src.getFirstName() != null && !src.getFirstName().isEmpty()){
            target.setFirstName(src.getFirstName());
        }
        if(src.getLastName() != null && !src.getLastName().isEmpty()){
            target.setLastName(src.getLastName());
        }
        if(src.getPassword() != null && !src.getPassword().isEmpty()){
            target.setPassword(src.getPassword().getBytes());
        }
        if(src.getDepartmentId() != null){
            DepartmentEntity dep = departmentService.findById(src.getDepartmentId());
            if(dep == null){
                throw new RuntimeException(String.format("No department with id = %d", src.getDepartmentId()));
            }
            target.setDepartment(departmentService.findById(src.getDepartmentId()));
        }
        if(src.getRole() != null && !src.getRole().isEmpty()){
            UserRole role = UserRole.valueOf(src.getRole());
            if(role == null){
                throw new RuntimeException(String.format("No user role '%s'", src.getRole()));
            }
            target.setRole(role);
        }
        if(src.getStatus() != null && !src.getStatus().isEmpty()){
            UserStatus status = UserStatus.valueOf(src.getStatus());
            if(status == null){
                throw new RuntimeException(String.format("No user status '%s'", src.getRole()));
            }
            target.setStatus(status);
        }

        return target;
    }
}
