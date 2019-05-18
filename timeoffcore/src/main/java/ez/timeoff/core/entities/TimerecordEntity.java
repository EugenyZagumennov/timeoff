package ez.timeoff.core.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Entity class for table 'TimerecordEntity'
 *
 * @author Evgeniy Zagumennov
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(schema = "timeoff", name = "timerecord")
public class TimerecordEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", length = 16 )
    private UUID uuid;

    @NotNull
    @Column(name = "timestamp", nullable = false)
    private long timestamp;

    @ManyToOne
    @JoinColumn(name = "user_fk", nullable = false)
    private UserEntity user;

    @NotNull
    @Column(name = "hours", nullable = false)
    private double hours;

    @ManyToOne
    @JoinColumn(name = "task_fk", nullable = false)
    private TaskEntity task;

    public TimerecordEntity(long timestamp, UserEntity user, double hours, TaskEntity task) {
        this.timestamp = timestamp;
        this.user = user;
        this.hours = hours;
        this.task = task;
    }

    @Override
    public String toString() {
        return "TimerecordEntity{" +
                "uuid=" + uuid +
                ", timestamp=" + timestamp +
                ", hours=" + hours +
                ", task=" + task +
                ", user=" + user +
                '}';
    }
}
