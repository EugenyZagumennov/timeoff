package ez.timeoff.controllers;

import ez.timeoff.core.dto.CreateUserDto;
import ez.timeoff.core.entities.UserEntity;
import ez.timeoff.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid CreateUserDto userDto, Map<String, Object> model){
        List<UserEntity> foundUsers = userService.findByUsername(userDto.getUsername());

        if(!foundUsers.isEmpty()){
            model.put("message", "User exists!");
            return "registration";
        }

        userService.createNewUser(userDto);

        return "redirect:/login";
    }
}