package ez.timeoffcore.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
@Entity(name = "tasks")
@Table(name = "tasks", schema = "timeoff")
public class Task {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", length = 16 )
    private UUID uuid;

    @Column(name = "string_id")
    private String stringId;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Timerecord> timerecords = new ArrayList<>();

    public Task(String stringId, String description) {
        this.stringId = stringId;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "uuid=" + uuid +
                ", stringId='" + stringId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
