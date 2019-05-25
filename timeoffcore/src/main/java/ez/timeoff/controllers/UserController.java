package ez.timeoff.controllers;

import ez.timeoff.core.dto.CreateUserDto;
import ez.timeoff.core.entities.DepartmentEntity;
import ez.timeoff.core.entities.UserEntity;
import ez.timeoff.core.service.DepartmentService;
import ez.timeoff.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String getAllUsers(Map<String, Object> model) {
        List<UserEntity> users = userService.findAll();
        List<DepartmentEntity> deps = departmentService.findAll();
        model.put("users", users);
        model.put("departments", deps);
        return "users";
    }

    @PostMapping
    public String addUser(@Valid CreateUserDto userDto, Map<String, Object> model) {
        userService.createNewUser(userDto);

        List<UserEntity> users = userService.findAll();
        List<DepartmentEntity> deps = departmentService.findAll();
        model.put("users", users);
        model.put("departments", deps);
        return "users";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String name, Map<String, Object> model) {
        List<UserEntity> users;
        if(name == null || name.isEmpty()){
            users = userService.findAll();
        }else {
            users = userService.findByNameLike(name);
        }
        model.put("users", users);

        return "users";
    }
}
