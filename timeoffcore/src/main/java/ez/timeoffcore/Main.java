package ez.timeoffcore;

import ez.timeoffcore.entities.Department;
import ez.timeoffcore.entities.User;
import ez.timeoffcore.service.DepartmentService;
import ez.timeoffcore.service.TaskService;
import ez.timeoffcore.service.TimerecordService;
import ez.timeoffcore.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring.xml");
        DepartmentService departmentService = (DepartmentService) context.getBean("departmentService");
        UserService userService = (UserService) context.getBean("userService");
        TaskService taskService = (TaskService) context.getBean("taskService");
        TimerecordService timerecordService = (TimerecordService) context.getBean("timerecordService");

        List<Department> deps = departmentService.getAll();
        System.out.println(deps);
        System.out.println("//---------------------------");
        //---------------------------
        Department newDep = new Department("WebDepartment", new Date());
        UUID uuid = departmentService.save(newDep);
        System.out.println(uuid);
        System.out.println("//---------------------------");
        newDep = departmentService.get(uuid);
        System.out.println(deps);
        System.out.println("//---------------------------");
        //----------------------------
        List<User> users = userService.getAll();
        System.out.println(users);
        System.out.println("//---------------------------");
        //----------------------------
        User newUser1 = new User("Test1", "Test1", new Date(),
                MessageDigest.getInstance("MD5").digest("qwerty".getBytes()),
                newDep);

        User newUser2 = new User("Test2", "Test2", new Date(),
                MessageDigest.getInstance("MD5").digest("qwerty".getBytes()),
                newDep);

        userService.save(newUser1);
        userService.save(newUser2);
        users = userService.getAll();
        departmentService.remove(newDep);
        deps = departmentService.getAll();
        users = userService.getAll();
        deps = departmentService.getAll();
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
        List<Task> tasks = taskDao.findAllWithTimerecords();
        System.out.println(tasks);
        List<Timerecord> trs = timerecordDao.findAll();
        System.out.println(trs);
        System.out.println("//---------------------------");
        //---------------------------
        task = tasks.get(0);
        System.out.println(task.getStringId()+": "+task.getTimerecords());
        System.out.println(newUser.getName()+": "+newUser.getDepartment());
        System.out.println(newUser.getName()+": "+task.getTimerecords());

        List<User> usersWithTimerecords = userDao.findAllWithTimerecords();
        System.out.println(usersWithTimerecords);*/
    }
}
