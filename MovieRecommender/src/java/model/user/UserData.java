/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.user;

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

   /**
    * generic constructor
    */
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
     *
     * @return p = password
     */
    public boolean checkP(String p) {
        return p.equals(password);
    }

    /**
     * Set major
     *
     * @param major
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * return the major of the user
     */
    public String getMajor() {
        return major;
    }

    /**
     * Get the userid of the UserData
     *
     * @return
     */
    public int getUserId() {
        return this.userId;
    }

    /**
     * sets the userId of the userData
     *
     * @param id
     */
    public void setUserId(int id) {
        this.userId = id;
    }

    /**
     * returns the ban status of the user
     *
     * @return
     */
    public boolean getBanned() {
        return this.banned;
    }

    /**
     * sets the ban status of the user
     *
     * @param bool
     */
    public void setBanned(boolean bool) {
        this.banned = bool;
    }

    /**
     * Sets the name of this user
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets the name for this user
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets whether this user has admin privileges
     *
     * @param adminPriv
     */
    public void setAdmin(boolean b) {
        this.admin = b;
    }

    /**
     * returns if the user is an admin
     *
     * @return
     */
    public boolean getAdmin() {
        return this.admin;
    }

    /**
     * Gets the username of this user
     *
     * @return username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Sets the username of this user
     *
     * @param username
     */
    public void setUsername(String un) {
        this.username = username;
    }

    /**
     * Sets the password of this user
     *
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
