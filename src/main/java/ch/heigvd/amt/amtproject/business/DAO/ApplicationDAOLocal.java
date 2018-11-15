package ch.heigvd.amt.amtproject.business.DAO;

import ch.heigvd.amt.amtproject.model.Application;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ApplicationDAOLocal extends IGenericDAO<Application> {

    public Long create(Application project) ;

    public List<Application> getProjectsAll() ;

    public List<Application> getAppByPage(String email, int pageNumber, int pageSize);

    public Application findByApiKey(int id) ;

    public List<Application> getProjectsByUser(String email) ;

    public Long bindAppToUser(long idApp, long idUser) ;

    public int getLastApiKey() ;

}
