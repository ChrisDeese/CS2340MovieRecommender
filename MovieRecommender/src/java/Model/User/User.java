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

    private String password;
    private String username;
    
    @ManagedProperty("#{userManager}")
    private UserManager userManager;
    
    /**
     * Creates a new instance of User
     */
    public User() {
        
    }
    
    public String getUN() {
        return this.username;
    }
    
    public String getPW() {
        return this.password;
    }
    
    public void setUN(String un) {
        this.username = un;
    }
    
    public void setPW(String p) {
        this.password = p;
    }
    
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
            return "SucessPage";
    }
    
    public void setUserManager(UserManager um) {
        userManager = um;
    }
    
}
