package ez.timeoffcore.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
@ToString
@EqualsAndHashCode
@Entity(name = "departments")
@Table(name = "departments", schema = "timeoff")
public class Department {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)", length = 16 )
    private UUID uuid;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "createDate", nullable = false)
    private Date createDate;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    List<User> users = new ArrayList<>();

    public Department(String name, Date createDate) {
        this.name = name;
        this.createDate = createDate;
    }
}