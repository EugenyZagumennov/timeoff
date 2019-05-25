package ez.timeoff.core.entities;

import ez.timeoff.core.entities.enums.TaskStatus;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
* Entity class for table 'Tasks'
*
* @author Evgeniy Zagumennov
*/
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@NamedEntityGraph(
        name = "TaskEntity.timerecords",
        attributeNodes = {
                @NamedAttributeNode(value = "timerecords", subgraph = "timerecords"),
        }
)
@Entity
@Table(schema = "timeoff", name = "task")
public class TaskEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", length = 16 )
    private UUID uuid;

    @NotNull
    @Column(name = "string_id", nullable = false)
    private String stringId;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_fk")
    private UserEntity user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    List<TimerecordEntity> timerecords = new ArrayList<>();

    public TaskEntity(String stringId, String description, TaskStatus status) {
        this.stringId = stringId;
        this.description = description;
        this.status = status;
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "stringId='" + stringId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
