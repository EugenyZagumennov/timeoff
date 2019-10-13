package ez.timeoff.core.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
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
