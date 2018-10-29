package ch.heigvd.amt.amtproject.business.DAO;

import java.util.List;

public interface IGenericDAO<T> {
    public Long create(T t);

    public T createAndReturnManagedEntity(T t);

    public void update(T t);

    public void delete(T t);

    public long count();

    public T findById(Long id);

    public List<T> findAll();
}
