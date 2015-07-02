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
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author chris
 */
@ManagedBean
@SessionScoped
public class User implements Serializable{
    private String username;
    private String password;
    private String name;
    private String major;
    private boolean admin;

    private String input;

    @ManagedProperty("#{userManager}")
    private UserManager userManager;
    
    private static SessionFactory factory1;

    /**
     * Creates a new instance of User
     */
    public User() {
        username = "";
        password = "";
        name = "";
        major="";
        input = "";
         try{
         factory1 = new Configuration().configure().buildSessionFactory();
      }catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
    }
    
    public String getMajor() {
        return this.major;
    }
    
    public void setMajor(String major) {
        this.major = major;
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
     * Returns name of this user
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the real name of this user
     * @param name
     */
     public void setName(String name) {
        this.name =name;
    }

    /**
     * Sets username of this user
     * @param username
     */
    public void setUsername(String un) {
        this.username = un;
    }

    /**
     * Sets password of this user
     * @param password
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
            return "index.xhtml?faces-redirect=true";
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
        userManager.addUser(data);
        System.out.println("Registration Success");
        return "index";
    }

    /**
     * Edits the profile of the user, including name, password, and admin privileges
     * @return page redirect
     */
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
        }
        
        UserData data = userManager.find(username);
        Session session = factory1.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            System.out.println(data.getUserId());
            UserData sqldata = (UserData) session.load(UserData.class, data.getUserId());
            data.setName(name);
            sqldata.setName(name);
            data.setPassword(password);
            sqldata.setPassword(password);
            data.setAdmin(admin);
            sqldata.setAdmin(admin);
            session.save(sqldata);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println("Profile Edited");
        return "ViewProfile";
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
     * @param userManager
     */
    public void setUserManager(UserManager um) {
        userManager = um;
    }

}
