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
    private String password;
    

    
    @ManagedProperty("#{userManager}")
    private UserManager userManager;
    
    /**
     * Creates a new instance of User
     */
    public User() {
        
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        //System.out.println(this.password);
        return this.password;
    }
    
    public void setUsername(String un) {
        this.username = un;
    }
    
    public void setPassword(String p) {
        this.password = p;
    }
    
    public String login() {
        UserData data = userManager.find(username);
        //password = this.getPassword();
        System.out.println(this.password.length());
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
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "welcomePage.xhtml?faces-redirect=true";
    }
    
    public String cancel() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "welcomePage.xhtml?faces-redirect=true";
    }
    
    public void setUserManager(UserManager um) {
        userManager = um;
    }
    
}
