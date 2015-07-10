/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import org.hibernate.HibernateException;
import org.hibernate.Query;
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
        UserData u = userManager.getMap().get(this.username);
        return u.getMajor();
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

        boolean banCheck = isBanned(this.username);

        if (banCheck == true) {
          FacesContext context = FacesContext.getCurrentInstance();
          context.addMessage(null, new FacesMessage("You are banned from using"
              + " MovieBuzz! Please contact the admin."));
          return null;
        }

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

    public List<UserData> getList() {
        Session session1 = factory1.openSession();
        List<UserData> answer = (List) new ArrayList<UserData>();
        Transaction tx1 = null;
        try {
            tx1 = session1.beginTransaction();
            String hql = "SELECT name, username, banned FROM UserData WHERE admin = False";
            Query query = session1.createQuery(hql);
            answer = query.list();
            tx1.commit();
        } catch (Exception e) {
            if (tx1 != null) tx1.rollback();
            e.printStackTrace();
        } finally {
            session1.close();
        }
        return answer;
    }

    // public void ban(UserData u) {
    //   Session session2 = factory1.openSession();
    //   Transaction tx2 = null;
    //   try {
    //       tx2 = session2.beginTransaction();
    //       String username = u.getUsername();
    //       String hql = "UPDATE UserData SET banned = :banned Where username = '" + username + "'";
    //       Query query = session2.createQuery(hql);
    //       query.setParameter("banned", "True");
    //       int answer = query.executeUpdate();
    //       tx2.commit();
    //   } catch (Exception e) {
    //       if (tx2 != null) tx2.rollback();
    //       e.printStackTrace();
    //   } finally {
    //       session2.close();
    //   }
    // }
    //
    // public void UnBan(UserData u) {
    //   Session session2 = factory1.openSession();
    //   Transaction tx2 = null;
    //   try {
    //       tx2 = session2.beginTransaction();
    //       String username = u.getUsername();
    //       String hql = "UPDATE UserData SET banned = :banned Where username = '" + username + "'";
    //       Query query = session2.createQuery(hql);
    //       query.setParameter("banned", "True");
    //       int answer = query.executeUpdate();
    //       tx2.commit();
    //   } catch (Exception e) {
    //       if (tx2 != null) tx2.rollback();
    //       e.printStackTrace();
    //   } finally {
    //       session2.close();
    //   }
    // }

//    public void changeBan(UserData u) {
//      Session session2 = factory1.openSession();
//      Query query;
//      Transaction tx2 = null;
//      try {
//          tx2 = session2.beginTransaction();
//          String username = u.getUsername();
//
//          if (!this.isBanned(username)) {
//              String hql = "UPDATE UserData SET banned = :banned Where username = '" + username + "'";
//              query = session2.createQuery(hql);
//              query.setParameter("banned", "True");
//          } else {
//              String hql = "UPDATE UserData SET banned = :banned Where username = '" + username + "'";
//              query = session2.createQuery(hql);
//              query.setParameter("banned", "False");
//          }
//          int answer = query.executeUpdate();
//          tx2.commit();
//      } catch (Exception e) {
//          if (tx2 != null) tx2.rollback();
//          e.printStackTrace();
//      } finally {
//          session2.close();
//      }
//    }


    public boolean isBanned(String username) {
        Session session1 = factory1.openSession();
        List<Boolean> answer = (List) new ArrayList<Boolean>();
        Transaction tx1 = null;
        try {
            tx1 = session1.beginTransaction();
            String hql = "SELECT banned From UserData Where username = '" + username + "'";
              Query query = session1.createQuery(hql);
              answer = query.list();
              tx1.commit();
        } catch (Exception e) {
            if (tx1 != null) tx1.rollback();
            e.printStackTrace();
        } finally {
            session1.close();
        }

        boolean ans = answer.get(0);
        return ans;
    }
    
    /**
     * checks to see if the user is an admin or not
+  *
     * @return the boolean for the disabled button
     */
     public boolean checkAdmin() {
         return !userManager.find(this.username).getAdmin();
     }

}
