package ch.heigvd.amt.amtproject.business.DAO;

import ch.heigvd.amt.amtproject.model.User;

import javax.ejb.Local;
import java.io.InputStream;
import java.util.List;

@Local
public interface UserDAOLocal extends IGenericDAO<User> {

    public Long create(User user);

    public void updateName(Long id, String firstName, String lastName);

    public void updateEmail(Long id, String email);

    public void updateAvatar(Long id, InputStream fileContent);

    public String getAvatar(Long id);

    public User findById(Long id);

    public User findByIdEmail(String userEmail);

    public List<User> findAll();

    public boolean isExist(String userEmail);

    public boolean isValid(String emailUser, String password);

}
