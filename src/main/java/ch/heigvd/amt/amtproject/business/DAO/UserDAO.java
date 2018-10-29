package ch.heigvd.amt.amtproject.business.DAO;
import ch.heigvd.amt.amtproject.model.User;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserDAO implements IGenericDAO<User>{
    @Override
    public Long create(User user) {
        return null;
    }

    @Override
    public User createAndReturnManagedEntity(User user) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    public User findByIdEmail(String emailUser) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    public boolean isExist(String emailUser, String password){
        return true;
    }

    public boolean isExist(String emailUser){
        return true;
    }
}
