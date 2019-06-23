package ez.timeoff.core.entities;

import ez.timeoff.core.entities.enums.UserRole;
import ez.timeoff.core.entities.enums.UserStatus;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.time.Instant;

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
@SequenceGenerator(name = "users_seq", sequenceName = "users_seq", initialValue = 1, allocationSize = 1)
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @Column(updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "login", length = 100, nullable = false)
    private String login;

    @NotNull
    @Size(max = 200)
    @Column(name = "firstName", length = 200, nullable = false)
    private String firstName;

    @NotNull
    @Size(max = 200)
    @Column(name = "lastName", length = 200, nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "createdDate", nullable = false)
    private Instant createdDate;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "department_fk", nullable = false)
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

    public UserEntity(String login, String firstName, String lastName, Instant createdDate, String password, DepartmentEntity department, UserRole role) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.createdDate = createdDate;
        this.password = password;
        this.department = department;
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    public String getFullName(){
        return firstName+" "+lastName;
    }

    public boolean isAdmin(){
        return role == UserRole.USER || role == UserRole.SUPERADMIN;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(role);
    }

    @Override
    public String getUsername() {
        return getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return status == UserStatus.ACTIVE;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status == UserStatus.ACTIVE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status == UserStatus.ACTIVE;
    }
}
