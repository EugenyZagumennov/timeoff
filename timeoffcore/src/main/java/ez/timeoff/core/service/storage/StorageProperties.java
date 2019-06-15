package ez.timeoff.core.service.storage;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("storage")
public class StorageProperties {

    @Value("${upload.path}")
    private String uploadPath;

}
