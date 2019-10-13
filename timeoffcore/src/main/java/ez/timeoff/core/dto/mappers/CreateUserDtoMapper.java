package ez.timeoff.core.dto.mappers;

import ez.timeoff.core.dto.CreateUserDto;
import ez.timeoff.core.entities.DepartmentEntity;
import ez.timeoff.core.entities.UserEntity;
import ez.timeoff.core.service.DepartmentService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class CreateUserDtoMapper {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "department", expression = "java(getDepartment(dto))")
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(dto.getPassword()))")
    public abstract UserEntity map(CreateUserDto dto);

    protected DepartmentEntity getDepartment(CreateUserDto dto){
        return departmentService.findById(dto.getDepartmentId());
    }
}
