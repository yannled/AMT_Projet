package ch.heigvd.amt.amtproject.business.DAO;

import ch.heigvd.amt.amtproject.model.Application;

import java.util.List;

public interface ApplicationDAOLocal extends IGenericDAO<Application> {

    public Long create(Application project);

    public List<Application> getProjectsAll();

    public List<Application> getProjectsPage(int pageNumber);

    public Application findByApiKey(int id);

}
