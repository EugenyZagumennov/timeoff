package ez.timeoff.core.service;

import ez.timeoff.core.dto.CreateDepartmentDto;
import ez.timeoff.core.dto.mappers.CreateDepartmentDtoMapper;
import ez.timeoff.core.entities.DepartmentEntity;
import ez.timeoff.core.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.util.List;

/**
 * DepartmentEntity service class
 *
 * @author Evgeniy Zagumennov
 */
@Service
@Transactional
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private CreateDepartmentDtoMapper mapper;

    public DepartmentEntity save(DepartmentEntity department){
        return departmentRepository.save(department);
    }

    public DepartmentEntity findById(Long id){
        return departmentRepository.findById(id).orElse(null);
    }

    public List<DepartmentEntity> findAll(){
        return departmentRepository.findAll();
    }

    public void delete(DepartmentEntity department){
        departmentRepository.delete(department);
    }

    public List<DepartmentEntity> findByNameLike(String name) {
        return departmentRepository.findByNameContaining(name);
    }

    public DepartmentEntity createNewDepartment(CreateDepartmentDto dto){
        if(dto.getParentId() != null) {
            Long parentId = dto.getParentId();
            DepartmentEntity foundDepartment = findById(parentId);

            if (foundDepartment == null) {
                throw new ValidationException(String.format("Department with id=%d doesn't exist!", parentId));
            }
        }

        DepartmentEntity departmentEntiry = mapper.map(dto);
        save(departmentEntiry);

        return departmentEntiry;
    }
}
