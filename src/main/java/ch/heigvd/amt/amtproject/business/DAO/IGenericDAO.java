package ch.heigvd.amt.amtproject.business.DAO;

import java.util.List;

public interface IGenericDAO<T> {
    public Long create(T t) throws Exception;

    public T createAndReturnManagedEntity(T t);

    public void update(T t) throws Exception;

    public void delete(T t) throws Exception;

    public long count();

    public T findById(Long id) throws Exception;

    public List<T> findAll() throws Exception;
}
