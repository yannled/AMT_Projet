package ch.heigvd.amt.amtproject.business.DAO;
import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.business.PasswordUtils;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Stateless
public class UserDAO implements IGenericDAO<User>, UserDAOLocal {

    private final String createUser = "INSERT INTO tbUser (userFirstName, userLastName ,userEmail, userPassword, privilegeId, statusId, userAvatar) VALUES (?,?,?,?,?,?,?)";

    private final String updateUser = "UPDATE tbUser SET userFirstName = (?), userLastName = (?), userEmail = (?), privilegeId = (?), statusId = (?) WHERE userId = (?)";
    private final String updateUserName = "UPDATE tbUser SET userFirstName = (?), userLastName = (?) WHERE userId = (?)";
    private final String updateUserState = "UPDATE tbUser SET statusId = (?) WHERE userEmail = (?)";
    private final String updateUserAdmin = "UPDATE tbUser SET privilegeId = (?) WHERE userEmail = (?)";
    private final String updateUserEmail = "UPDATE tbUser SET userEmail = (?) WHERE userId = (?)";
    private final String updateUserAvatar = "UPDATE tbUser SET userAvatar =(?) WHERE userId = (?)";
    private final String updateUserPassword = "UPDATE tbUser SET userPassword =(?) WHERE userId = (?)";


    private final String getUserEmailPassword = "SELECT userEmail, userPassword FROM tbUser WHERE userEmail = (?)";
    private final String getUsers = "SELECT userId, userLastName, userFirstName, userEmail, privilegeId, statusId, userAvatar FROM  tbUser";
    private final String getUserById = "SELECT userId, userLastName, userFirstName, userEmail, privilegeId, statusId, userAvatar FROM  tbUser WHERE userId = (?)";
    private final String getUserByEmail = "SELECT userId, userLastName, userFirstName, userEmail, privilegeId, statusId, userAvatar FROM  tbUser WHERE userEmail = (?)";
    private final String getAvatar = "SELECT userAvatar FROM  tbUser WHERE userId = (?)";
    private final String getCountUserEmail = "SELECT count(*) AS total FROM tbUser WHERE userEmail = (?)";
    private final String countNbrOfApplications = "SELECT count(tbProject.projectId) AS total FROM tbProject JOIN tbUserProject on tbUserProject.projectId = tbProject.projectId JOIN tbUser on tbUser.userId = tbUserProject.userId WHERE tbUser.userEmail = (?)";


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
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.isAdmin() ? 1 : 0 );
            ps.setInt(6, 1);
            ps.setBlob(7, this.base64ToBlob(user.getBase64Avatar()));

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
    public void update(User user){

    }

    @Override
    public void updateState(User user){
      try (Connection connection = dataSource.getConnection()) {
        PreparedStatement ps = connection.prepareStatement(updateUserState);

        // insert data into statement.
        ps.setInt(1, user.getState());
        ps.setString(2, user.getEmail());
        ps.execute();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    @Override
    public void updateAdmin(User user){
      try (Connection connection = dataSource.getConnection()) {
        PreparedStatement ps = connection.prepareStatement(updateUserAdmin);

        // insert data into statement.
        ps.setInt(1, user.isAdmin()? 1 : 0);
        ps.setString(2, user.getEmail());
        ps.execute();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    }

    @Override
    public void updateName(Long id, String firstName, String lastName) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(updateUserName);

            // insert data into statement.
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setLong(3, id);

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateEmail(Long id, String email) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(updateUserEmail);

            // insert data into statement.
            ps.setString(1, email);
            ps.setLong(2, id);

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateAvatar(Long id, InputStream avatarFile) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(updateUserAvatar);

            // insert data into statement.
            ps.setBlob(1, avatarFile);
            ps.setLong(2, id);

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePassword(Long id, String hashPassword){
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(updateUserPassword);

            // insert data into statement.
            ps.setString(1, hashPassword);
            ps.setLong(2, id);

            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void delete(User user) {

    }

    // source : https://www.codejava.net/coding/how-to-display-images-from-database-in-jsp-page-with-java-servlet
    @Override
    public String getAvatar(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(getAvatar);

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            String base64Avatar;

            if (rs.next()) {
                Blob blob = rs.getBlob("userAvatar");

                base64Avatar = blobToBase64(blob);

            } else {
                return null;
            }
            return base64Avatar;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public User findById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(getUserById);

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return this.rsToUser(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findByIdEmail(String userEmail) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(getUserByEmail);

            ps.setString(1, userEmail);
            ResultSet rs = ps.executeQuery();

            User user;
            if (rs.next()) {
                user = this.rsToUser(rs);
            } else {
                return null;
            }
            return user;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(getUsers);

            ResultSet rs = ps.executeQuery();

            List<User> users = new ArrayList<>();
            int i = 0;
            while (rs.next()) {
                User user = this.rsToUser(rs);
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

    public boolean isExist(String userEmail){
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(getCountUserEmail);

            ps.setString(1, userEmail);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total") != 0;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int countNumbersApplications(String userEmail){
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(countNbrOfApplications);

            ps.setString(1, userEmail);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String blobToBase64(Blob blob) {
        try {
            String base64Str;
            InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] imageBytes = outputStream.toByteArray();
            base64Str = Base64.getEncoder().encodeToString(imageBytes);


            inputStream.close();
            outputStream.close();

            return base64Str;
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Blob base64ToBlob(String base64Str) {
        try {
            byte[] buffer =  Base64.getDecoder().decode(base64Str);

            return new SerialBlob(buffer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private User rsToUser(ResultSet rs) {
        try {
            User user = new User();
            user.setId(rs.getLong("userId"));
            user.setLastName(rs.getString("userLastName"));
            user.setName(rs.getString("userFirstName"));
            user.setEmail(rs.getString("userEmail"));
            user.setState(rs.getInt("statusId"));
            user.setBase64Avatar(this.blobToBase64(rs.getBlob("userAvatar")));
            int value = rs.getInt("privilegeId");
            if (value == 0) {
                user.setAdmin(false);
            }
            if (value == 1) {
                user.setAdmin(true);
            }

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
