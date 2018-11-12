package ez.timeoffcore.dao;

import java.util.List;

/**
 * DAO interface
 *
 * @author Evgeniy Zagumennov
 * @param <T> entity type
 */
public interface IDao<T> {
    void save(T entity);
    List<T> getAll();
}
