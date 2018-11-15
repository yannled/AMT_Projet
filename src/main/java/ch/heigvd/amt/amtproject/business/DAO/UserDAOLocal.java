package ch.heigvd.amt.amtproject.business.DAO;

import ch.heigvd.amt.amtproject.model.User;

import javax.ejb.Local;
import java.io.InputStream;
import java.util.List;

@Local
public interface UserDAOLocal extends IGenericDAO<User> {

    Long create(User user) throws Exception;

    void updateState(User user) throws Exception;

    void updateAdmin(User user) throws Exception;

    void updateName(Long id, String firstName, String lastName) throws Exception;

    void updateEmail(Long id, String email) throws Exception;

    void updateAvatar(Long id, InputStream fileContent) throws Exception;

    void updatePassword(Long id, String hashPassword) throws Exception;

    String getAvatar(Long id) throws Exception;

    User findById(Long id) throws Exception;

    User findByIdEmail(String userEmail) throws Exception;

    List<User> findAllByProjectId(Long projectId);

    List<User> findAll() throws Exception;

    boolean isExist(String userEmail) throws Exception;

    boolean isValid(String emailUser, String password) throws Exception;

    int countNumbersApplications(String email) throws Exception;

    void updateProfil(User currentUser, InputStream is, Boolean changeAvatar, String email, String firstName, String lastName);

}
