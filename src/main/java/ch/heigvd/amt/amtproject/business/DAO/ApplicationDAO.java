package ch.heigvd.amt.amtproject.business.DAO;
import ch.heigvd.amt.amtproject.model.Application;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ApplicationDAO implements IGenericDAO<Application>, ApplicationDAOLocal {

    private final String createApplication = "INSERT INTO tbProject (projectName, projectDescrition ,APIKey, APISecret) VALUES (?,?,?,?)";
    private final String modifyApplication = "UPDATE tbProject SET projectName = ?, projectDescrition = ? WHERE APIKey = ? AND APISecret = ?";
    private final String getProjectsByPage = "SELECT projectName, projectDescrition, projectCreationDate, APIKey, APISecret FROM tbProject   " +
            "ORDER BY projectId OFFSET 10 * ((?) - 1) ROWS FETCH NEXT 10 ROWS ONLY;";
    private final String getProjects = "SELECT projectName, projectDescrition, projectCreationDate, APIKey, APISecret FROM tbProject";
    private final String getProjectsByApiKey = "SELECT projectId, projectName, projectDescrition, APIKey,  APISecret FROM tbProject WHERE APIKey = (?)";
    private final String getProjectById = "SELECT projectId, projectName, projectDescrition, APIKey,  APISecret FROM tbProject WHERE projectId = (?)";
    private final String deleteProject = "DELETE FROM tbProject WHERE APIKey = ?";
    private final String deleteRelationBetweenAppAndUser = "DELETE FROM tbUserProject WHERE projectId = ?";
    private final String getProjectsByUser = "SELECT tbProject.projectId, projectName, projectDescrition, projectCreationDate, APIKey, APISecret FROM tbProject JOIN tbUserProject on tbUserProject.projectId = tbProject.projectId JOIN tbUser on tbUser.userId = tbUserProject.userId WHERE tbUser.userEmail = (?)";
    private final String bindAppToUser = "INSERT INTO tbUserProject (userId, projectId) VALUES (?,?)";


    @Resource(name = "jdbc/AMTProject")
    DataSource dataSource;

    @Override
    public Long create(Application application){
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(createApplication);

            // insert data into statement.
            ps.setString(1, application.getName());
            ps.setString(2, application.getDescription());
            ps.setInt(3, application.getApikey());
            ps.setString(4, application.getApiSecret());

            ps.execute();

            PreparedStatement ps2 = connection.prepareStatement(getProjectsByApiKey);
            ps2.setInt(1, application.getApikey());

            ps2.execute();
            ResultSet rs = ps2.executeQuery();

            if (rs.next()) {
                return (long)rs.getInt("projectId");
            }
            return -1L;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long bindAppToUser(long idApp, long idUser){
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(bindAppToUser);

            // insert data into statement.
            ps.setInt(1, (int)idUser);
            ps.setInt(2, (int)idApp);

            ps.execute();
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Application> getProjectsAll() {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(getProjects);

            ps.execute();
            ResultSet rs = ps.executeQuery();

            List<Application> projectList = new ArrayList<>();
            while (rs.next()) {
                Application application = new Application();
                application.setName(rs.getString("projectName"));
                application.setDescription(rs.getString("projectDescrition"));
                application.setApikey(rs.getInt("APIKey"));
                application.setApiSecret(rs.getString("APISecret"));

                projectList.add(application);
            }
            return projectList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Application> getProjectsByUser(String email) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(getProjectsByUser);
            ps.setString(1, email);
            ps.execute();
            ResultSet rs = ps.executeQuery();

            List<Application> projectList = new ArrayList<>();
            while (rs.next()) {
                Application application = new Application();
                application.setId(rs.getInt("tbProject.projectId"));
                application.setName(rs.getString("projectName"));
                application.setDescription(rs.getString("projectDescrition"));
                application.setApikey(rs.getInt("APIKey"));
                application.setApiSecret(rs.getString("APISecret"));

                projectList.add(application);
            }
            return projectList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO could be used to get only the requested pages from the database
    public List<Application> getProjectsPage(int pageNumber) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(getProjectsByPage);

            // insert data into statement.
            ps.setInt(1, pageNumber);

            ps.execute();
            ResultSet rs = ps.executeQuery();

            List<Application> projectList = new ArrayList<>();
            while (rs.next()) {
                Application application = new Application();
                application.setName(rs.getString("projectName"));
                application.setDescription(rs.getString("projectDescrition"));
                application.setApikey(rs.getInt("APIKey"));
                application.setApiSecret(rs.getString("APISecret"));

                projectList.add(application);
            }

            return projectList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Application createAndReturnManagedEntity(Application application) {
        return null;
    }

    @Override
    public void update(Application application) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(modifyApplication);

            // insert data into statement.
            ps.setString(1, application.getName());
            ps.setString(2, application.getDescription());
            ps.setInt(3, application.getApikey());
            ps.setString(4, application.getApiSecret());

            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Application application) {
        int idApplication = 0;
        try (Connection connection = dataSource.getConnection()) {

            //Get id application
            PreparedStatement getProject = connection.prepareStatement(getProjectsByApiKey);

            // insert data into statement.
            getProject.setInt(1, application.getApikey());

            getProject.execute();
            ResultSet rs = getProject.executeQuery();
            while (rs.next()) {
                idApplication = rs.getInt("projectId");
            }
            //Delete relation between the app and the user
            PreparedStatement deleteBetweenUserAndApp = connection.prepareStatement(deleteRelationBetweenAppAndUser);
            deleteBetweenUserAndApp.setInt(1, idApplication);
            deleteBetweenUserAndApp.execute();

            //Delete the app
            PreparedStatement deleteApp = connection.prepareStatement(deleteProject);
            // insert data into statement.
            deleteApp.setInt(1, application.getApikey());

            deleteApp.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Application findById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(getProjectById);

            // insert data into statement.
            ps.setLong(1, id);

            ps.execute();
            ResultSet rs = ps.executeQuery();
            Application application = new Application();
            while (rs.next()) {
                application.setId(rs.getInt("projectId"));
                application.setName(rs.getString("projectName"));
                application.setDescription(rs.getString("projectDescrition"));
                application.setApikey(rs.getInt("APIKey"));
                application.setApiSecret(rs.getString("APISecret"));
            }

            return application;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Application findByApiKey(int apiKey) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(getProjectsByApiKey);

            // insert data into statement.
            ps.setInt(1, apiKey);

            ps.execute();
            ResultSet rs = ps.executeQuery();
            Application application = new Application();
            while (rs.next()) {
                application.setName(rs.getString("projectName"));
                application.setDescription(rs.getString("projectDescrition"));
                application.setApikey(rs.getInt("APIKey"));
                application.setApiSecret(rs.getString("APISecret"));
            }

            return application;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Application> findAll() {
        return null;
    }
}