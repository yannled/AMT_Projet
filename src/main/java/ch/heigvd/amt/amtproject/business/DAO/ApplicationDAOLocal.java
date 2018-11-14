package ch.heigvd.amt.amtproject.business.DAO;

import ch.heigvd.amt.amtproject.model.Application;

import java.util.List;

public interface ApplicationDAOLocal extends IGenericDAO<Application> {

    public Long create(Application project) throws Exception;

    public List<Application> getProjectsAll() throws Exception;

    public List<Application> getProjectsPage(int pageNumber) throws Exception;

    public Application findByApiKey(int id) throws Exception;

    public List<Application> getProjectsByUser(String email) throws Exception;

    public Long bindAppToUser(long idApp, long idUser) throws Exception;

}
