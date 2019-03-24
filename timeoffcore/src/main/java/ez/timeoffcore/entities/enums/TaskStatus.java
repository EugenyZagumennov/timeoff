package ez.timeoffcore.entities.enums;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "timeoff")
public enum TaskStatus {
    OPEN, IN_PROGRESS, DONE, REOPENED, CLOSED;
}
