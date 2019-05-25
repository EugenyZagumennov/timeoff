package ez.timeoff.core.dto.mappers;

import ez.timeoff.core.dto.CreateUserDto;
import ez.timeoff.core.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CreateUserDtoMapper {

    CreateUserDtoMapper INSTANCE = Mappers.getMapper( CreateUserDtoMapper.class );

    @Mapping(target = "regDate", expression = "java(new java.util.Date())")
    @Mapping(target = "password", source = "password.bytes")
    @Mapping(target = "department", ignore = true)
    UserEntity map(CreateUserDto dto);
}
