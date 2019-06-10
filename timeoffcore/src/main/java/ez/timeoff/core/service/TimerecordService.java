package ez.timeoff.core.service;

import ez.timeoff.core.repositories.TimerecordRepository;
import ez.timeoff.core.entities.TimerecordEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * TimerecordEntity service class
 *
 * @author Evgeniy Zagumennov
 */
@Service
@Transactional
public class TimerecordService {

    @Autowired
    private TimerecordRepository timerecordRepository;

    public TimerecordEntity save(TimerecordEntity timerecord){
        return timerecordRepository.save(timerecord);
    }

    public TimerecordEntity findById(Long id){
        return timerecordRepository.findById(id).orElse(null);
    }

    public List<TimerecordEntity> findAll(){
        return timerecordRepository.findAll();
    }

    public void delete(TimerecordEntity timerecord){
        timerecordRepository.delete(timerecord);
    }
}
