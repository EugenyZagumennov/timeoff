package ez.timeoffcore;

import ez.timeoffcore.dao.*;
import ez.timeoffcore.entities.Department;
import ez.timeoffcore.entities.User;
import ez.timeoffcore.service.DepartmentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring.xml");
        UserDao userDao = (UserDao) context.getBean("userDao");
        //DepartmentDao departmentDao = (DepartmentDao) context.getBean("departmentDao");
        //TaskDao taskDao = (TaskDao) context.getBean("taskDao");
        //TimerecordDao timerecordDao = (TimerecordDao) context.getBean("timerecordDao");
        DepartmentService departmentService = (DepartmentService) context.getBean("departmentService");

        List<Department> deps = departmentService.getAllDepartments();
        System.out.println(deps);
        System.out.println("//---------------------------");
        //---------------------------
        Department newDep = new Department("WebDepartment", new Date());
        UUID uuid = departmentService.createNewDepartment(newDep);
        System.out.println(uuid);
        System.out.println("//---------------------------");
        newDep = departmentService.getDepartment(uuid);
        System.out.println(deps);
        System.out.println("//---------------------------");
        //----------------------------
        List<User> users = userDao.findAll();
        System.out.println(users);
        System.out.println("//---------------------------");
        //----------------------------
        User newUser1 = new User("Test1", "Test1", new Date(),
                MessageDigest.getInstance("MD5").digest("qwerty".getBytes()),
                newDep);

        User newUser2 = new User("Test2", "Test2", new Date(),
                MessageDigest.getInstance("MD5").digest("qwerty".getBytes()),
                newDep);

        userDao.save(newUser1);
        userDao.save(newUser2);
        users = userDao.findAll();
        departmentService.removeDepartment(newDep);
        deps = departmentService.getAllDepartments();
        users = userDao.findAll();
        deps = departmentService.getAllDepartments();
        System.out.println();
       /* System.out.println(users);
        System.out.println("//---------------------------");
        //---------------------------
        System.out.println(deps);
        deps = departmentDao.findAll();
        System.out.println(deps);
        System.out.println("//---------------------------");
        //---------------------------
        Task task = new Task("TO-1", "My first task");
        Timerecord tr = new Timerecord((new Date()).getTime(), newUser, 4, task);
        taskDao.save(task);
        timerecordDao.save(tr);
        List<Task> tasks = taskDao.getAllWithTimerecords();
        System.out.println(tasks);
        List<Timerecord> trs = timerecordDao.findAll();
        System.out.println(trs);
        System.out.println("//---------------------------");
        //---------------------------
        task = tasks.get(0);
        System.out.println(task.getStringId()+": "+task.getTimerecords());
        System.out.println(newUser.getName()+": "+newUser.getDepartment());
        System.out.println(newUser.getName()+": "+task.getTimerecords());

        List<User> usersWithTimerecords = userDao.getAllWithTimerecords();
        System.out.println(usersWithTimerecords);*/
    }
}
