package ch.heigvd.amt.amtproject.business.DAO;

import ch.heigvd.amt.amtproject.model.User;

import javax.ejb.Local;

@Local
public interface UserDAOLocal extends IGenericDAO<User>{

    public Long create(User user);

}
