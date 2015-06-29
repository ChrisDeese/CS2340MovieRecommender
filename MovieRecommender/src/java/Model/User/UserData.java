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
    private String major;
    private boolean admin;
    private boolean banned;
    private int userId;

    //To add later
    /*private String bio;
    private String location;
    private int age;*/

    
    UserData() {
        
    }
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
     * Set major
     * @param major 
     */
    public void setMajor(String major) {
        this.major = major;
    }
    
    public String getMajor() {
        return major;
    }
 
    public int getUserId() {
        return this.userId;
    }
    
    public void setUserId(int id) {
        this.userId = id;
    }
    
    public boolean getBanned() {
        return this.banned;
    }    
    
    public void setBanned(boolean bool) {
        this.banned=bool;
    }
       
    /**
     * Sets the name of this user
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name; 
    }
    
    /**
     * Sets whether this user has admin privileges
     * @param adminPriv
     */
    public void setAdmin(boolean b) {
        this.admin = b;
    }
    
    public boolean getAdmin() {
        return this.admin;
    }

    /**
     * Gets the username of this user
     * @return username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the username of this user
     * @param username
     */
    public void setUsername(String un) {
        this.username = username;
    }

    /**
     * Sets the password of this user
     * @param password
     */
    public void setPassword(String pw) {
        this.password = pw;
    }
    
    public String getPassword() {
        return this.password;
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
