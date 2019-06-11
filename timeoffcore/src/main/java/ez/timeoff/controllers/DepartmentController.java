package ez.timeoff.controllers;

import ez.timeoff.core.dto.CreateDepartmentDto;
import ez.timeoff.core.entities.DepartmentEntity;
import ez.timeoff.core.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String getDepartments(@RequestParam(required = false, defaultValue = "") String filter,  Map<String, Object> model) {
        List<DepartmentEntity> departments;

        if(filter.isEmpty()){
            departments = departmentService.findAll();
        }else {
            departments = departmentService.findByNameLike(filter);
        }

        model.put("departments", departments);
        model.put("filter", filter);
        return "departments";
    }

    @PostMapping
    public String addDepartment(@Valid CreateDepartmentDto dto, Map<String, Object> model) {
        departmentService.createNewDepartment(dto);

        List<DepartmentEntity> deps = departmentService.findAll();
        model.put("departments", deps);

        return "departments";
    }
}
