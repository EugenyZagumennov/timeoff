package ez.timeoff.core.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 *
 * @author Evgeniy Zagumennov
 */
@Data
public class CreateUserDto {
    @NotEmpty
    private String login;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String password;

    @NotEmpty
    private String departmentId;
}
