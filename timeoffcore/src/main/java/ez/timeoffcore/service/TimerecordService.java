package ez.timeoffcore.service;

import ez.timeoffcore.dao.TimerecordDao;
import ez.timeoffcore.entities.Timerecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Timerecord service class
 *
 * @author Evgeniy Zagumennov
 */
@Service("timerecordService")
@Transactional
public class TimerecordService {

    @Autowired
    private TimerecordDao timerecordDao;

    public UUID createNew(Timerecord timerecord){
        return timerecordDao.save(timerecord);
    }

    public Timerecord get(UUID uuid){
        return timerecordDao.find(uuid);
    }

    public List<Timerecord> getAll(){
        return timerecordDao.findAll();
    }

    public Timerecord merge(Timerecord timerecord){
        return timerecordDao.merge(timerecord);
    }

    public void remove(Timerecord timerecord){
        timerecordDao.remove(timerecord);
    }
}
