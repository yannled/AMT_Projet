package ch.heigvd.amt.amtproject.business.DAO;

import ch.heigvd.amt.amtproject.model.User;

import javax.ejb.Local;
import java.io.InputStream;
import java.util.List;

@Local
public interface UserDAOLocal extends IGenericDAO<User> {

    Long create(User user) ;

    void updateState(User user) ;

    void updateAdmin(User user) ;

    void updateName(Long id, String firstName, String lastName) ;

    void updateEmail(Long id, String email) ;

    void updateAvatar(Long id, InputStream fileContent) ;

    void updatePassword(Long id, String hashPassword) ;

    String getAvatar(Long id) ;

    User findById(Long id) ;

    User findByIdEmail(String userEmail) ;

    List<User> findAllByProjectId(Long projectId);

    List<User> findAll() ;

    boolean isExist(String userEmail) ;

    boolean isValid(String emailUser, String password) ;

    int countNumbersApplications(String email) ;

    void updateProfil(User currentUser, InputStream is, Boolean changeAvatar, String email, String firstName, String lastName);

}
