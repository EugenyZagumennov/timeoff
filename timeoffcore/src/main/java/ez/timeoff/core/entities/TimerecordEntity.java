package ez.timeoff.core.entities;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
@SequenceGenerator(name = "timerecords_seq", sequenceName = "timerecords_seq", initialValue = 1, allocationSize = 1)
public class TimerecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "timerecords_seq")
    @Column(updatable = false, nullable = false)
    private Long id;

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
                "id=" + id +
                ", timestamp=" + timestamp +
                ", hours=" + hours +
                ", task=" + task +
                ", user=" + user +
                '}';
    }
}
