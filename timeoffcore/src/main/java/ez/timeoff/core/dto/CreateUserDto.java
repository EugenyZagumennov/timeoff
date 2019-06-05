package ez.timeoff.core.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateUserDto {
    @NotEmpty
    private String username;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    //@NotEmpty //TODO
    private String departmentUuid;
}
