package ez.timeoffcore.dao;

import java.util.List;
import java.util.UUID;

/**
 * DAO interface
 *
 * @author Evgeniy Zagumennov
 * @param <T> entity type
 */
public interface IDao<T> {
    UUID save(T entity);
    List<T> getAll();

    default List<T> getAllWithTimerecords(){
        return null;
    }
}
