/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.User;

/**
 *
 * @author chris
 *
 * This class to be deleted once database is implemented.
 */
public class UserData {


    private String username;
    private String password;
    private String name;
    private boolean admin;

    //To add later
    /*private String name;
    private String bio;
    private String location;
    private int age;*/

    /**
     * Creates a new instance of User
     */
    UserData(String name, String pass) {
        this.username = name;
        this.password = pass;
        this.name = "";
    }

    /**
     * Checks entered password versus saved password
     * @return p = password
     */
    boolean checkP(String p) {
        return (p.equals(password));
    }

    /**
     * Sets the name of this user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets whether this user has admin privileges
     */
    public void setAdmin(boolean b) {
        this.admin = b;
    }

    /**
     * Gets the username of this user
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /*public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public String getLocation() {
        return location;
    }

    public int getAge() {
        return age;
    }*/
}
