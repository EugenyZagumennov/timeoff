package ez.timeoff.core.entities.enums;

import javax.persistence.Table;

@Table(schema =  "timeoff")
public enum UserStatusEntity {
    CREATED, ACTIVE, INACTIVE, DELETED;
}