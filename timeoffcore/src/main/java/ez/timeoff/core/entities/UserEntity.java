package ez.timeoff.core.entities;

import ez.timeoff.core.entities.enums.UserRole;
import ez.timeoff.core.entities.enums.UserStatus;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * Entity class for table 'UserEntity'
 *
 * @author Evgeniy Zagumennov
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode
@NamedEntityGraph(
        name = "UserEntity.tasks",
        attributeNodes = {
                @NamedAttributeNode(value = "tasks", subgraph = "tasks"),
        }
        //, subgraphs = @NamedSubgraph(name = "timerecords", attributeNodes = @NamedAttributeNode("product"))
)
@Entity
@Table(schema = "timeoff", name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", length = 16 )
    private UUID uuid;

    @NotNull
    @Size(max = 100)
    @Column(name = "username", length = 100, nullable = false)
    private String username;

    @NotNull
    @Size(max = 200)
    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @NotNull
    @Column(name = "regDate", nullable = false)
    private Date regDate;

    @NotNull
    @Column(name = "password", nullable = false)
    private byte[] password;

    @ManyToOne
    @JoinColumn(name = "department_fk"/*, nullable = false*/) //TODO
    private DepartmentEntity department;

    @NotNull
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @NotNull
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.CREATED;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskEntity> tasks = new ArrayList<>();

    public UserEntity(String username, String name, Date regDate, byte[] password, DepartmentEntity department, UserRole role) {
        this.username = username;
        this.name = name;
        this.regDate = regDate;
        this.password = password;
        this.department = department;
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
