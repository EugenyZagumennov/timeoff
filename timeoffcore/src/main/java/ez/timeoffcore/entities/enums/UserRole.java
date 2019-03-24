package ez.timeoffcore.entities.enums;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema =  "timeoff")
public enum UserRole {
    USER, EDITOR, ADMIN, SUPERADMIN;
}
