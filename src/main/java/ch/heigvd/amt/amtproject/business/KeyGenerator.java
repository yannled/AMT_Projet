package ch.heigvd.amt.amtproject.business;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

public class KeyGenerator {

    private static int autoKey = 0;

    public static int generateNumberKey(){
        return autoKey++;
    }

    public static String generateRandomKey(){
        String uniqueID = UUID.randomUUID().toString();
        uniqueID += String.valueOf(autoKey++);
        return uniqueID;
    }

    public static String generateRandomPassword(int length){
        if (length < 6)
            throw new IllegalArgumentException("min size = 6");
        return RandomStringUtils.randomAlphabetic(length);
    }
}
