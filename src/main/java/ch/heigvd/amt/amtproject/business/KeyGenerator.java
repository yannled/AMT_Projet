package ch.heigvd.amt.amtproject.business;

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
}
