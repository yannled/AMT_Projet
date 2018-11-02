package ch.heigvd.amt.amtproject.business.DAO;
import ch.heigvd.amt.amtproject.model.Application;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ApplicationDAO implements IGenericDAO<Application>, ApplicationDAOLocal {

    private final String getProjectsByPage = "SELECT projectName, projectDescription, projectCreationDate, APIKey, APISecret FROM tbProject   " +
            "ORDER BY projectId OFFSET 10 * ((?) - 1) ROWS FETCH NEXT 10 ROWS ONLY;";
    private final String getProjects = "SELECT projectName, projectDescrition, projectCreationDate, APIKey, APISecret FROM tbProject";


    @Resource(name = "jdbc/AMTProject")
    DataSource dataSource;

    @Override
    public Long create(Application application) {
        return null;
    }

    public List<Application> getProjectsAll(){
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(getProjects);

            ps.execute();
            ResultSet rs = ps.executeQuery();

            List<Application> projectList = new ArrayList<>();
            while (rs.next()) {
                Application application = new Application();
                application.setName(rs.getString("projectName"));
                application.setDescription(rs.getString("projectDescrition")); // TODO correct projectDescription name in database
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
    public List<Application> getProjectsPage(int pageNumber){
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
                application.setDescription(rs.getString("projectDescription"));
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
