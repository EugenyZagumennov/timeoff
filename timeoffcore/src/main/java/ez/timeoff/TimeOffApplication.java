package ez.timeoff;

import ez.timeoff.core.service.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ez.timeoff")
@EnableConfigurationProperties(StorageProperties.class)
public class TimeOffApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeOffApplication.class, args);
    }
}