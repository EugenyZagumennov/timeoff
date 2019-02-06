package ez.timeoffcore.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Entity class for table 'Timerecord'
 *
 * @author Evgeniy Zagumennov
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "timerecords")
@Table(name = "timerecords", schema = "timeoff")
public class Timerecord {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", length = 16 )
    private UUID uuid;

    @NotNull
    @Column(name = "timestamp", nullable = false)
    private long timestamp;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "users_uuid", nullable = false)
    private User user;

    @NotNull
    @Column(name = "hours", nullable = false)
    private double hours;

    @ManyToOne
    @JoinColumn(name = "tasks_uuid", nullable = false)
    private Task task;

    public Timerecord(long timestamp, User user, double hours, Task task) {
        this.timestamp = timestamp;
        this.user = user;
        this.hours = hours;
        this.task = task;
    }

    @Override
    public String toString() {
        return "Timerecord{" +
                "uuid=" + uuid +
                ", timestamp=" + timestamp +
                ", hours=" + hours +
                ", task=" + task +
                ", user=" + user +
                '}';
    }
}
