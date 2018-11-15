package ch.heigvd.amt.amtproject.business.DAO;

import java.util.List;

public interface IGenericDAO<T> {
    Long create(T t) throws Exception;

    T createAndReturnManagedEntity(T t);

    void update(T t) throws Exception;

    void delete(T t) throws Exception;

    long count();

    T findById(Long id) throws Exception;

    List<T> findAll() throws Exception;
}
