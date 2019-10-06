package ez.timeoff.core.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Evgeniy Zagumennov
 */
@Data
public class CreateUserDto {
    @NotBlank(message = "Введите логин!")
    private String login;

    @NotBlank(message = "Введите имя!")
    private String firstName;

    @NotBlank(message = "Введите фамилию!")
    private String lastName;

    @NotBlank(message = "Введите пароль!")
    private String password;

    @NotNull(message = "Выберите департамент!")
    private Long departmentId;
}
