package ez.timeoff.controllers;

import ez.timeoff.core.dto.CreateDepartmentDto;
import ez.timeoff.core.entities.DepartmentEntity;
import ez.timeoff.core.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String getAllDepartments(Map<String, Object> model) {
        List<DepartmentEntity> departments = departmentService.findAll();
        model.put("departments", departments);
        return "departments";
    }

    @PostMapping
    public String addDepartment(@Valid CreateDepartmentDto dto, Map<String, Object> model) {
        departmentService.createNewDepartment(dto);

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
