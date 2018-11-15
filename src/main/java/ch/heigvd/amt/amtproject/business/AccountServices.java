package ch.heigvd.amt.amtproject.business;
import ch.heigvd.amt.amtproject.business.DAO.UserDAO;
import ch.heigvd.amt.amtproject.model.RightException;
import ch.heigvd.amt.amtproject.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class AccountServices {

    @EJB
    UserDAO userDAO;

    public int suspendAccount(String emailUserToSuspend) throws Exception {
        User user = userDAO.findByIdEmail(emailUserToSuspend);
        if(!user.isAdmin()){
            throw new RightException("You need to be admin to suspend an account");
        }
        user.setState(0);
        userDAO.update(user);
        return 1;
    }

    public int resetPassword(String emailAdministrator, String emailUserToReset, int length ) throws Exception {
        User administrator = userDAO.findByIdEmail(emailAdministrator);
        User user = userDAO.findByIdEmail(emailUserToReset);
        if(!administrator.isAdmin()){
            throw new RightException("You need to be admin to suspend an account");
        }
        String newPassword = KeyGenerator.generateRandomPassword(length);
        user.setPassword(newPassword);
        userDAO.update(user);
        return 1;
    }

    public int changePrivilege(String emailAdministrator, String emailUserToChangePrivilege, int newState ) throws Exception {
        User administrator = userDAO.findByIdEmail(emailAdministrator);
        User user = userDAO.findByIdEmail(emailUserToChangePrivilege);
        if(!administrator.isAdmin()){
            throw new RightException("You need to be admin to suspend an account");
        }
        user.setState(newState);
        userDAO.update(user);
        return 1;
    }
}
