package ch.heigvd.amt.amtproject.business.DAO;

import ch.heigvd.amt.amtproject.model.Application;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ApplicationDAOLocal extends IGenericDAO<Application> {

    Long create(Application project) throws Exception;

    List<Application> getProjectsAll() throws Exception;

    List<Application> getProjectsPage(int pageNumber) throws Exception;

    Application findByApiKey(int id) throws Exception;

    List<Application> getProjectsByUser(String email) throws Exception;

    Long bindAppToUser(long idApp, long idUser) throws Exception;

}
