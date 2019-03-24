package ez.timeoffcore.entities;

import ez.timeoffcore.entities.enums.TaskStatus;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.EnumType.STRING;

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
        name = "Task.timerecords",
        attributeNodes = {
                @NamedAttributeNode(value = "timerecords", subgraph = "timerecords"),
        }
)
@Entity
@Table(schema = "timeoff")
public class Task {

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Timerecord> timerecords = new ArrayList<>();

    public Task(String stringId, String description, TaskStatus status) {
        this.stringId = stringId;
        this.description = description;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "stringId='" + stringId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
