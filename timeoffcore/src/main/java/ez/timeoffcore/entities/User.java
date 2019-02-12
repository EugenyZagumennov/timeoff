package ez.timeoffcore.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * Entity class for table 'User'
 *
 * @author Evgeniy Zagumennov
 */

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@NamedEntityGraph(
        name = "User.timerecords",
        attributeNodes = {
                @NamedAttributeNode(value = "timerecords", subgraph = "timerecords"),
        }
        //, subgraphs = @NamedSubgraph(name = "timerecords", attributeNodes = @NamedAttributeNode("product"))
)
@Entity
@Table(schema = "timeoff")
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", length = 16 )
    private UUID uuid;

    @NotNull
    @Size(max = 100)
    @Column(name = "login", length = 100, nullable = false)
    private String login;

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
    @JoinColumn(name = "department_fk", nullable = false)
    private Department department;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Timerecord> timerecords = new ArrayList<>();

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
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
