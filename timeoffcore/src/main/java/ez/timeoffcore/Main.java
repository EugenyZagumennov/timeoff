package ez.timeoffcore;

import ez.timeoffcore.dao.IDao;
import ez.timeoffcore.entities.Department;
import ez.timeoffcore.entities.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring.xml");
        IDao userDao = (IDao) context.getBean("userDao");
        IDao departmentDao = (IDao) context.getBean("departmentDao");

        List<Department> deps = departmentDao.getAll();
        System.out.println(deps);
        Department newDep = new Department("WebDepartment", new Date());
        departmentDao.save(newDep);
        deps = departmentDao.getAll();
        System.out.println(deps);

        List<User> users = userDao.getAll();
        System.out.println(users);

        User newUser = new User(
                "ipupkin",
                "Ivan Pupkin",
                new Date(),
                MessageDigest.getInstance("MD5").digest("qwerty".getBytes()),
                newDep);

        userDao.save(newUser);
        users = userDao.getAll();
        System.out.println(users);

    }

}
