/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.User;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author chris
 */
@ManagedBean
@SessionScoped
public class User implements Serializable{
    private String username;
    private String password;
    private String email;
    private boolean admin;

    @ManagedProperty("#{userManager}")
    private UserManager userManager;

    /**
     * Creates a new instance of User
     */
    public User() {

    }

    /**
     * Returns username of this user
     * @return username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Returns password of this user
     * @return password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Returns email of this user
     * @return email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets username of this user
     */
    public void setUsername(String un) {
        this.username = un;
    }

    /**
     * Sets password of this user
     */
    public void setPassword(String p) {
        this.password = p;
    }

    /**
     * Logs in user. If password is wrong, login fails
     * @return page redirect
     */
    public String login() {
        UserData data = userManager.find(username);

        if (data == null || !data.checkP(password)) {
            username="";
            password="";
            System.out.println("No such user found or password wrong");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Username or Password incorrect"));
            return null;
        }
        System.out.println("Login Success");
            return "SuccessPage";
    }

    /**
     * Logs out user
     * @return page redirect
     */
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "welcomePage.xhtml?faces-redirect=true";
    }

    /**
     * Registers user. If username is already taken, registration fails
     * @return page redirect
     */
    public String register() {
        if (username == null || password == null || email == null) {
            System.out.println("Input is null.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Username/Password/Email is null"));
            return null;
        } else if (userManager.find(username) != null) {
            System.out.println("Username already taken, please choose another.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Username already taken"));
            return null;
        }

        UserData data = new UserData(username, password);
        data.setEmail(email);
        data.setAdmin(admin);
        userManager.addUser(data);
        System.out.println("Registration Success");
        return "index";
    }

    /**
     * Cancels login/registration
     * @return page redirect
     */
    public String cancel() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "welcomePage.xhtml?faces-redirect=true";
    }

    /**
     * Sets the user manager for this user, which is used as the database
     */
    public void setUserManager(UserManager um) {
        userManager = um;
    }

}
