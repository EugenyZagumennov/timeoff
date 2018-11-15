package ez.timeoffcore.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import sun.security.provider.MD5;

import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
@ToString
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

    @Column(name = "regdate", nullable = false)
    private Date regDate;

    @Column(name = "password", nullable = false)
    private byte[] password;


    public User(String login, String name, Date regDate, byte[] password) {
        this.login = login;
        this.name = name;
        this.regDate = regDate;
        this.password = password;
    }
}
