package ez.timeoffcore.entities.enums;

import lombok.NoArgsConstructor;

import javax.persistence.Table;

@Table(schema =  "timeoff")
public enum UserStatusEntity {
    CREATED, ACTIVE, INACTIVE, DELETED;
}
