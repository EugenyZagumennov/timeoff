package ez.timeoff.core.dto;

import javax.validation.constraints.NotNull;

import ez.timeoff.core.entities.enums.UserRole;
import lombok.Data;
/**
 *
 * @author Evgeniy Zagumennov
 */
@Data
public class UpdateUserDto {
    @NotNull
    private Long id;

    private String login;
    private String firstName;
    private String lastName;
    private String password;
    private Long departmentId;
    private String role;
    private String status;
}
