package ez.timeoff.controllers;

import ez.timeoff.core.entities.DepartmentEntity;
import ez.timeoff.core.service.DepartmentService;
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
@RequestMapping("/departments")
public class DepartmentController {
    
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String getAllUsers(Map<String, Object> model) {
        List<DepartmentEntity> users = departmentService.findAll();
        model.put("departments", users);
        return "departments";
    }

    @PostMapping
    public String addUser(@RequestParam String name, Map<String, Object> model
    ) {
        DepartmentEntity dep = new DepartmentEntity(name, new Date());
        departmentService.save(dep);

        List<DepartmentEntity> deps = departmentService.findAll();
        model.put("departments", deps);

        return "departments";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String name, Map<String, Object> model) {
        List<DepartmentEntity> deps;
        if(name == null || name.isEmpty()){
            deps = departmentService.findAll();
        }else {
            deps = departmentService.findByNameLike(name);
        }
        model.put("departments", deps);

        return "departments";
    }
}
