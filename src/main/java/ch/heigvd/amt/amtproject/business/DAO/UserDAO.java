package ch.heigvd.amt.amtproject.business.DAO;
import ch.heigvd.amt.amtproject.model.Application;
import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.business.PasswordUtils;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserDAO implements IGenericDAO<User>, UserDAOLocal{

    private final String createUser = "INSERT INTO tbUser (userFirstName, userLastName ,userEmail, userSel, userPassword, privilegeId, statusId) VALUES (?,?,?,?,?,?,?)";
    private final String getUserEmailPassword = "SELECT userEmail, userPassword FROM tbUser WHERE userEmail = (?)";
    private final String getUsers ="SELECT userLastName, userFirstName, userEmail, privilegeId, statusId FROM  tbUser";

    @Resource(name = "jdbc/AMTProject")
    DataSource dataSource;

    @Override
    public Long create(User user) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(createUser);

            // insert data into statement.
            ps.setString(1, user.getName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, "");
            ps.setString(5, user.getPassword());
            ps.setInt(6, user.isAdmin() ? 1 : 0 );
            ps.setInt(7, 1);

            ps.execute();

            return null;//rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(getUsers);

            ps.execute();
            ResultSet rs = ps.executeQuery();

            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setLastName(rs.getString("userLastName"));
                user.setName(rs.getString("userFirstName")); // TODO correct projectDescription name in database
                user.setEmail(rs.getString("userEmail"));
                user.setState(rs.getInt("statusId"));
                int value = rs.getInt("privilegeId");
                if(value == 0) {
                    user.setAdmin(false);
                }
                if(value == 1) {
                    user.setAdmin(true);
                }
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isValid(String emailUser, String password) {
        String email;
        String passwordHash;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(getUserEmailPassword);

            // insert data into statement.
            ps.setString(1, emailUser);

            ps.execute();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                email = rs.getString("userEmail");
                passwordHash = rs.getString("userPassword");
            } else {
                return false;
            }

            return PasswordUtils.validatePassword(password, passwordHash);

        } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean isExist(String emailUser){
        return true;
    }
}
