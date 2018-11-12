package ez.timeoffcore;

import ez.timeoffcore.dao.UserDao;
import ez.timeoffcore.entities.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring.xml");
        UserDao daoImpl = (UserDao) context.getBean("daoImpl");
        List<User> users = daoImpl.getAll();
        System.out.println(users);
    }
}
