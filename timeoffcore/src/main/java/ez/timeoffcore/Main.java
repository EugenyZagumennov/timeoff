package ez.timeoffcore;

import ez.timeoffcore.dao.IDao;
import ez.timeoffcore.entities.Department;
import ez.timeoffcore.entities.Task;
import ez.timeoffcore.entities.Timerecord;
import ez.timeoffcore.entities.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.*;

import static com.sun.deploy.config.JREInfo.getAll;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring.xml");
        IDao userDao = (IDao) context.getBean("userDao");
        IDao departmentDao = (IDao) context.getBean("departmentDao");
        IDao taskDao = (IDao) context.getBean("taskDao");
        IDao timerecordDao = (IDao) context.getBean("timerecordDao");

        List<Department> deps = departmentDao.getAll();
        System.out.println(deps);
        System.out.println("//---------------------------");
        //---------------------------
        Department newDep = new Department("WebDepartment", new Date());
        departmentDao.save(newDep);
        deps = departmentDao.getAll();
        System.out.println(deps);
        System.out.println("//---------------------------");
        //----------------------------
        List<User> users = userDao.getAll();
        System.out.println(users);
        System.out.println("//---------------------------");
        //----------------------------
        User newUser = new User("ipupkin", "Ivan Pupkin", new Date(),
                MessageDigest.getInstance("MD5").digest("qwerty".getBytes()),
                newDep);

        userDao.save(newUser);
        users = userDao.getAll();
        System.out.println(users);
        System.out.println("//---------------------------");
        //---------------------------
        System.out.println(deps);
        deps = departmentDao.getAll();
        System.out.println(deps);
        System.out.println("//---------------------------");
        //---------------------------
        Task task = new Task("TO-1", "My first task");
        Timerecord tr = new Timerecord((new Date()).getTime(), newUser, 4, task);
        taskDao.save(task);
        timerecordDao.save(tr);
        List<Task> tasks = taskDao.getAll();
        System.out.println(tasks);
        List<Timerecord> trs = timerecordDao.getAll();
        System.out.println(trs);
        System.out.println("//---------------------------");
        //---------------------------
        System.out.println(task.getStringId()+": "+task.getTimerecords());
        System.out.println(newUser+": "+newUser.getDepartment());
        System.out.println(newUser+": "+newUser.getTimerecords());
        System.out.println(newDep+": "+newDep.getUsers());
    }
}
