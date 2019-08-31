package ez.timeoff.controllers;

import ez.timeoff.core.dto.CreateDepartmentDto;
import ez.timeoff.core.entities.DepartmentEntity;
import ez.timeoff.core.service.DepartmentService;
import ez.timeoff.core.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @Autowired
    private ValidationService validationService;

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
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addDepartment(@Valid CreateDepartmentDto dto,
                                BindingResult bindingResult,
                                Model model)
    {

        if(bindingResult.hasErrors()){
            model.addAllAttributes(validationService.getErrors(bindingResult));
        } else {
            departmentService.createNewDepartment(dto);
        }

        List<DepartmentEntity> deps = departmentService.findAll();
        model.addAttribute("departments", deps);
        model.addAttribute("filter", "");

        return "departments";
    }
}
