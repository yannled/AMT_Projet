package ch.heigvd.amt.amtproject.model;

import ch.heigvd.amt.amtproject.business.KeyGenerator;

public class Application {
    private int id;
    private String name;
    private String description;
    private String apiSecret;
    private int apikey;

    public Application(){}

    public Application(String name, String description){
        this.name = name;
        this.description = description;
        this.apikey = KeyGenerator.generateNumberKey();
        this.apiSecret = KeyGenerator.generateRandomKey();
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

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

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public int getApikey() {
        return apikey;
    }

    public void setApikey(int apikey) {
        this.apikey = apikey;
    }
}
