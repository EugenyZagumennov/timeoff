package ez.timeoffcore.service;

import ez.timeoffcore.dao.DepartmentDao;
import ez.timeoffcore.dao.UserDao;
import ez.timeoffcore.entities.Department;
import ez.timeoffcore.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Department service class
 *
 * @author Evgeniy Zagumennov
 */
@Service("departmentService")
@Transactional
public class DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    public UUID createNew(Department department){
        return departmentDao.save(department);
    }

    public Department get(UUID uuid){
        return departmentDao.find(uuid);
    }

    public List<Department> getAll(){
        return departmentDao.findAll();
    }

    public List<Department> getAllWithUsers(){
        return departmentDao.findAllWithUsers();
    }

    public Department merge(Department department){
        return departmentDao.merge(department);
    }

    public void remove(Department department){
        departmentDao.remove(department);
    }
}
