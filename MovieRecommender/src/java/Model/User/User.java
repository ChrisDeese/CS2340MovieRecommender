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
import javax.faces.context.Flash;

/**
 *
 * @author chris
 */
@ManagedBean
@SessionScoped
public class User implements Serializable{
    private String username;
    private String oldUsername;
    private String password;
    private String name;
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
        //System.out.println(this.password);
        return this.password;
    }

    /**
     * Returns name of this user
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the real name of this user
     */
     public void setName(String name) {
        this.name =name;
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

        //password = this.getPassword();
        //System.out.println(this.password.length());
        //System.out.println(data);
        if (this.username.length() == 0 && this.getPassword().length()== 0) {
            username="";
            password="";
            //System.out.println("No such user found or password wrong");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Username and "
                    + "password field empty."
                    + " Please fill out the required fields."));
            return null;
        }
        else if (this.username.length()> 0 && this.getPassword().length()== 0) {
            username="";
            password="";
            //System.out.println("No such user found or password wrong");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Password field empty."
                    + " Please fill out the required fields."));
            return null;
        }
        else if (this.username.length()== 0 && this.getPassword().length()> 0) {
            username="";
            password="";
            //System.out.println("No such user found or password wrong");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Username field empty."
                    + " Please fill out the required fields."));
            return null;
        }

        else if (data == null || !data.checkP(password)) {
            username="";
            password="";
            //System.out.println("No such user found or password wrong");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Username or Password incorrect"));
            return null;
        }
        System.out.println("Login Success");
            FacesContext facesContext = FacesContext.getCurrentInstance();
        Flash flash = facesContext.getExternalContext().getFlash();
        flash.setKeepMessages(true);
        flash.setRedirect(true);
        facesContext.addMessage(null, new FacesMessage(
        "You successfully logged in!",""));
            return "index";
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
        if (this.username.length() == 0 && this.getPassword().length() == 0) {
            username="";
            password="";
            //System.out.println("No such user found or password wrong");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Username and "
                    + "password field empty."
                    + " Please fill out the required fields."));
            return null;
        } else if (this.getPassword().length() == 0) {
            username="";
            password="";
            //System.out.println("No such user found or password wrong");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Password field empty."
                    + " Please fill out the required fields."));
            return null;
        } else if (this.username.length() == 0) {
            username="";
            password="";
            //System.out.println("No such user found or password wrong");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Username field empty."
                    + " Please fill out the required fields."));
            return null;
        } else if (userManager.find(username) != null) {
            System.out.println("Username already taken, please choose another.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Username already taken"));
            return null;
        }

        UserData data = new UserData(username, password);
        data.setName(name);
        data.setAdmin(admin);
        data.setPrevUN(username);
        userManager.addUser(data);
        oldUsername = data.getPrevUN();
        System.out.println("Registration Success");
        return "index";
    }

    public String editProf() {
        if (this.name.length() == 0) {
            username="";
            password="";
            //System.out.println("No such user found or password wrong");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Name field empty."
                    + " Please fill out the required fields."));
            return null;
        } else if (this.getPassword().length() == 0) {
            username="";
            password="";
            //System.out.println("No such user found or password wrong");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Password field empty."
                    + " Please fill out the required fields."));
            return null;
        } else if (this.username.length() == 0) {
            username="";
            password="";
            //System.out.println("No such user found or password wrong");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Username field empty."
                    + " Please fill out the required fields."));
            return null;
        } else if (oldUsername.equals(username) || userManager.find(username) != null) {
            System.out.println("Username already taken, please choose another.");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Username already taken"));
            return null;
        }

        UserData data = userManager.find(username);
        oldUsername = username;
        data.setName(name);
        data.setUsername(username);
        data.setPassword(password);
        data.setAdmin(admin);
        System.out.println("Profile Edited");
        return "viewProfile";
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
