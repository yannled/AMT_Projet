package ch.heigvd.amt.amtproject.business;

import ch.heigvd.amt.amtproject.business.DAO.ApplicationDAOLocal;
import ch.heigvd.amt.amtproject.business.DAO.UserDAOLocal;
import ch.heigvd.amt.amtproject.model.Application;
import org.apache.commons.lang3.RandomStringUtils;

import javax.ejb.EJB;
import java.util.UUID;

public class KeyGenerator {


   /* public int generateNumberKey() {
        try {
            return applicationDAO.getLastApiKey() + 1;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }*/

    public String generateRandomKey() {
        try {
            String uniqueID = UUID.randomUUID().toString();
            return uniqueID;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static String generateRandomPassword(int length) {
        if (length < 6)
            throw new IllegalArgumentException("min size = 6");
        return RandomStringUtils.randomAlphabetic(length);
    }
}
