package ez.timeoff.controllers;

import ez.timeoff.core.dto.CreateUserDto;
import ez.timeoff.core.dto.UpdateUserDto;
import ez.timeoff.core.entities.DepartmentEntity;
import ez.timeoff.core.entities.UserEntity;
import ez.timeoff.core.entities.enums.UserRole;
import ez.timeoff.core.entities.enums.UserStatus;
import ez.timeoff.core.service.DepartmentService;
import ez.timeoff.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public String getUsers(@RequestParam(required = false, defaultValue = "") String filter, Map<String, Object> model) {
        List<UserEntity> users;

        if(filter.isEmpty()){
            users = userService.findAll();
        }else {
            users = userService.findByFirstNameLike(filter);
        }

        List<DepartmentEntity> deps = departmentService.findAll();

        model.put("users", users);
        model.put("departments", deps);
        model.put("filter", filter);
        return "users";
    }

    @PostMapping
    public String addUser(@Valid CreateUserDto userDto, Map<String, Object> model) {
        List<DepartmentEntity> deps = departmentService.findAll();
        model.put("departments", deps);

        UserEntity foundUser = userService.findByLogin(userDto.getLogin());
        if(foundUser != null){
            model.put("message", "User exists!");
            return "users";
        }

        userService.createNewUser(userDto);
        List<UserEntity> users = userService.findAll();
        model.put("users", users);
        return "users";
    }

    @GetMapping("{user}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editUserForm(@PathVariable UserEntity user, Map<String, Object> model){
        List<DepartmentEntity> deps = departmentService.findAll();
        model.put("departments", deps);
        model.put("user", user);
        model.put("roles", UserRole.values());
        model.put("statuses", UserStatus.values());
        return "editUser";
    }

    @PostMapping("edit/{user}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editUser(@Valid UpdateUserDto dto, Map<String, Object> model){
        userService.updateUser(dto);
        return "redirect:/users";
    }
}
