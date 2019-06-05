package ez.timeoff.core.dto.mappers;

import ez.timeoff.core.dto.CreateUserDto;
import ez.timeoff.core.entities.DepartmentEntity;
import ez.timeoff.core.entities.UserEntity;
import ez.timeoff.core.service.DepartmentService;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class CreateUserDtoMapper {

    @Autowired
    private DepartmentService departmentService;

    @Mapping(target = "regDate", expression = "java(new java.util.Date())")
    @Mapping(target = "password", source = "password.bytes")
    @Mapping(target = "department", ignore = true/*expression = "java(getDepartment(dto))"*/) //TODO
    public abstract UserEntity map(CreateUserDto dto);

    protected DepartmentEntity getDepartment(CreateUserDto dto){
        return departmentService.findById(UUID.fromString(dto.getDepartmentUuid()));
    }
}
