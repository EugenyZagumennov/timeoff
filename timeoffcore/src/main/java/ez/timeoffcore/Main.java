package ez.timeoffcore;

import ez.timeoffcore.dao.*;
import ez.timeoffcore.entities.Department;
import ez.timeoffcore.entities.Task;
import ez.timeoffcore.entities.Timerecord;
import ez.timeoffcore.entities.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring.xml");
        UserDao userDao = (UserDao) context.getBean("userDao");
        DepartmentDao departmentDao = (DepartmentDao) context.getBean("departmentDao");
        TaskDao taskDao = (TaskDao) context.getBean("taskDao");
        TimerecordDao timerecordDao = (TimerecordDao) context.getBean("timerecordDao");

        List<Department> deps = departmentDao.getAll();
        System.out.println(deps);
        System.out.println("//---------------------------");
        //---------------------------
        Department newDep = new Department("WebDepartment", new Date());
        UUID uuid = departmentDao.save(newDep);
        System.out.println(uuid);
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
        List<Task> tasks = taskDao.getAllWithTimerecords();
        System.out.println(tasks);
        List<Timerecord> trs = timerecordDao.getAll();
        System.out.println(trs);
        System.out.println("//---------------------------");
        //---------------------------
        task = tasks.get(0);
        System.out.println(task.getStringId()+": "+task.getTimerecords());
        System.out.println(newUser.getName()+": "+newUser.getDepartment());
        System.out.println(newUser.getName()+": "+task.getTimerecords());

        List<User> usersWithTimerecords = userDao.getAllWithTimerecords();
        System.out.println(usersWithTimerecords);
    }
}
