package ch.heigvd.amt.amtproject.business.DAO;

import ch.heigvd.amt.amtproject.model.User;

import javax.ejb.Local;
import java.io.InputStream;
import java.util.List;

@Local
public interface UserDAOLocal extends IGenericDAO<User> {

    public Long create(User user) throws Exception;

    public void updateState(User user) throws Exception;

    public void updateAdmin(User user) throws Exception;

    public void updateName(Long id, String firstName, String lastName) throws Exception;

    public void updateEmail(Long id, String email) throws Exception;

    public void updateAvatar(Long id, InputStream fileContent) throws Exception;

    public void updatePassword(Long id, String hashPassword) throws Exception;

    public String getAvatar(Long id) throws Exception;

    public User findById(Long id) throws Exception;

    public User findByIdEmail(String userEmail) throws Exception;

    public List<User> findAll() throws Exception;

    public boolean isExist(String userEmail) throws Exception;

    public boolean isValid(String emailUser, String password) throws Exception;

    public int countNumbersApplications(String email) throws Exception;

}
