package ez.timeoffcore.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Entity class for table 'Department'
 *
 * @author Evgeniy Zagumennov
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@NamedQueries(
        @NamedQuery(
                name = "Department.findAllWithUsers",
                query = "select d from Department d JOIN FETCH d.users u"
        )
)
@Table(schema = "timeoff")
public class Department {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", length = 16 )
    private UUID uuid;

    @NotNull
    @Size(max = 200)
    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @NotNull
    @Column(name = "createDate", nullable = false)
    private Date createDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    List<User> users = new ArrayList<>();

    public Department(String name, Date createDate) {
        this.name = name;
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                '}';
    }
}