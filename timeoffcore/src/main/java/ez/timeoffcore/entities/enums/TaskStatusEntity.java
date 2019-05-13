package ez.timeoffcore.entities.enums;

import javax.persistence.Table;

@Table(schema = "timeoff")
public enum TaskStatusEntity {
    OPEN, IN_PROGRESS, DONE, REOPENED, CLOSED;
}
