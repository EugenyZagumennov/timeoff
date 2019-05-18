package ez.timeoff.controllers;

import ez.timeoff.core.entities.UserEntity;
import ez.timeoff.core.entities.enums.UserRoleEntity;
import ez.timeoff.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAllUsers(Map<String, Object> model) {
        List<UserEntity> users = userService.findAll();
        model.put("users", users);
        return "users";
    }

    @PostMapping
    public String addUser(@RequestParam String login,
                          @RequestParam String name,
                          @RequestParam String password,
                          Map<String, Object> model
    ) {
        UserEntity user = new UserEntity(login, name, new Date(), password.getBytes(),
                null, UserRoleEntity.USER);
        userService.save(user);

        List<UserEntity> users = userService.findAll();
        model.put("users", users);

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
