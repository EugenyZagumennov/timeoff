package ez.timeoff.core.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity class for table 'DepartmentEntity'
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
                name = "DepartmentEntity.findAllWithUsers",
                query = "select d from DepartmentEntity d JOIN FETCH d.users u"
        )
)
@Table(schema = "timeoff", name = "department")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Size(max = 200)
    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @NotNull
    @Column(name = "createdDate", nullable = false)
    private Instant createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_department_fk")
    private DepartmentEntity parentDepartment;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    List<UserEntity> users = new ArrayList<>();

    public DepartmentEntity(String name, Instant createdDate) {
        this.name = name;
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "DepartmentEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}