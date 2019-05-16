package ez.timeoffcore.service;

import ez.timeoffcore.dao.DepartmentRepository;
import ez.timeoffcore.entities.DepartmentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

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

    public DepartmentEntity findById(UUID uuid){
        return departmentRepository.findById(uuid).orElse(null);
    }

    public List<DepartmentEntity> findAll(){
        return departmentRepository.findAll();
    }

    public void delete(DepartmentEntity department){
        departmentRepository.delete(department);
    }
}
