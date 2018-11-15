package ch.heigvd.amt.amtproject.business.DAO;

import ch.heigvd.amt.amtproject.model.Application;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ApplicationDAOLocal extends IGenericDAO<Application> {

    public Long create(Application project) throws Exception;

    public List<Application> getProjectsAll() throws Exception;

    public List<Application> getAppByPage(String email, int pageNumber, int pageSize);

    public Application findByApiKey(int id) throws Exception;

    public List<Application> getProjectsByUser(String email) throws Exception;

    public Long bindAppToUser(long idApp, long idUser) throws Exception;

    public int getLastApiKey() throws Exception;

}
