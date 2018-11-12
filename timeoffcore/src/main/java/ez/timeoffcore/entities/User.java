package ez.timeoffcore.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity class for table 'User'
 *
 * @author Evgeniy Zagumennov
 */
@Entity(name = "user")
@Table(name = "user", schema = "timeoff")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "login", length = 20, nullable = false)
    private String login;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "reg_date", nullable = false)
    private Date regDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}
