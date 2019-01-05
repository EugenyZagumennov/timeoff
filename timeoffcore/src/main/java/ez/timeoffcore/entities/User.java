package ez.timeoffcore.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * Entity class for table 'User'
 *
 * @author Evgeniy Zagumennov
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "users")
@Table(name = "users", schema = "timeoff")
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", length = 16 )
    private UUID uuid;

    @Column(name = "login", length = 100, nullable = false)
    private String login;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "regDate", nullable = false)
    private Date regDate;

    @Column(name = "password", nullable = false)
    private byte[] password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departments_uuid", nullable = false)
    private Department department;

    public User(String login, String name, Date regDate, byte[] password, Department department) {
        this.login = login;
        this.name = name;
        this.regDate = regDate;
        this.password = password;
        this.department = department;
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid=" + uuid +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", regDate=" + regDate +
                ", password=" + Arrays.toString(password) +
                ", department=" + department.getName() +
                '}';
    }
}
