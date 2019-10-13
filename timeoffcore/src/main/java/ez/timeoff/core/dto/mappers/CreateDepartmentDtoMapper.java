package ez.timeoff.core.dto.mappers;

import ez.timeoff.core.dto.CreateDepartmentDto;
import ez.timeoff.core.entities.DepartmentEntity;
import ez.timeoff.core.service.DepartmentService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created on 07-Jun-19.
 *
 * @author ezagumennov
 */
@Mapper(componentModel = "spring")
public abstract class CreateDepartmentDtoMapper{

    @Autowired
    private DepartmentService departmentService;

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "parentDepartment", expression = "java(getParentDepartment(dto))")
    public abstract DepartmentEntity map(CreateDepartmentDto dto);

    protected DepartmentEntity getParentDepartment(CreateDepartmentDto dto){
        return dto.getParentId() != null ? departmentService.findById(dto.getParentId()) : null;
    }
}
