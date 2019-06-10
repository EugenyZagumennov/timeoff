package ez.timeoff.core.dto.mappers;

import ez.timeoff.core.dto.CreateUserDto;
import ez.timeoff.core.entities.DepartmentEntity;
import ez.timeoff.core.entities.UserEntity;
import ez.timeoff.core.service.DepartmentService;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class CreateUserDtoMapper {

    @Autowired
    private DepartmentService departmentService;

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "password", source = "password.bytes")
    @Mapping(target = "department", expression = "java(getDepartment(dto))")
    public abstract UserEntity map(CreateUserDto dto);

    protected DepartmentEntity getDepartment(CreateUserDto dto){
        return departmentService.findById(dto.getDepartmentId());
    }
}
