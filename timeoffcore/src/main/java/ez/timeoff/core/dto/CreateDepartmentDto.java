package ez.timeoff.core.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * Created on 07-Jun-19.
 *
 * @author Evgeniy Zagumennov
 */
@Data
public class CreateDepartmentDto {
    @NotEmpty
    private String name;

    private String parentId;
}
