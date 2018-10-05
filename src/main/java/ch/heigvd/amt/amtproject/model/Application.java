package ch.heigvd.amt.amtproject.model;

import ch.heigvd.amt.amtproject.business.KeyGenerator;

public class Application {
    private String name;
    private String description;
    private int apiKey;
    private String apiSecret;

    public Application(String name, String description){
        this.name = name;
        this.description = description;
        apiKey = KeyGenerator.generateNumberKey();
        apiSecret = KeyGenerator.generateRandomKey();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
