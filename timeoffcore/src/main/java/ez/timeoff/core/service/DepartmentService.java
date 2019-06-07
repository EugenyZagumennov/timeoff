package ez.timeoff.core.service;

import ez.timeoff.core.repositories.DepartmentRepository;
import ez.timeoff.core.entities.DepartmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.Instant;
import java.util.List;

/**
 * DepartmentEntity service class
 *
 * @author Evgeniy Zagumennov
 */
@Service("departmentService")
@Transactional
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

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

    public DepartmentEntity createNewDepartment(String name){
        DepartmentEntity departmentEntiry = new DepartmentEntity(name, Instant.now());
        departmentRepository.save(departmentEntiry);

        return departmentEntiry;
    }
}
