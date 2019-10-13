package ez.timeoff.core.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * Created on 07-Jun-19.
 *
 * @author Evgeniy Zagumennov
 */
@Data
public class CreateDepartmentDto {
    @NotEmpty(message = "Введите имя департамента!")
    private String name;

    private Long parentId;
}
