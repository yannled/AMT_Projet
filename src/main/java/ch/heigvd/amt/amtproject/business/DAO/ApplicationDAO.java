package ch.heigvd.amt.amtproject.business.DAO;
import ch.heigvd.amt.amtproject.model.Application;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.util.List;

@Stateless
public class ApplicationDAO implements IGenericDAO<Application> {

    @Resource(name = "jdbc/AMTCars")
    DataSource dataSource;

    @Override
    public Long create(Application application) {
        return null;
    }

    @Override
    public Application createAndReturnManagedEntity(Application application) {
        return null;
    }

    @Override
    public void update(Application application) {

    }

    @Override
    public void delete(Application application) {

    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Application findById(Long id) {
        return null;
    }

    public Application findByApiKey(int id) {
        return null;
    }

    @Override
    public List<Application> findAll() {
        return null;
    }
}
