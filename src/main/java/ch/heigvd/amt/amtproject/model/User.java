package ch.heigvd.amt.amtproject.model;

import ch.heigvd.amt.amtproject.business.KeyGenerator;

import javax.naming.Name;

public class User {

    private String name;
    private String lastName;
    private String password;
    private String email;
    private boolean admin;
    private int state; // 0 = ok, 1 = ban, 2 = hasChangePassword
    private String base64Avatar;

    public User(String name, String lastName, String password, String email, boolean admin, int state, String base64Avatar) {
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.admin = admin;
        this.state = state;
        this.base64Avatar = base64Avatar;
    }

    public User(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public String getBase64Avatar() {
        return base64Avatar;
    }

    public void setBase64Avatar(String base64Avatar) {
        this.base64Avatar = base64Avatar;
    }
}