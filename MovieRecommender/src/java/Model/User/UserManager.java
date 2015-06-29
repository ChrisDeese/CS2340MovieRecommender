/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
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
@ManagedBean (name = "userManager")
@ApplicationScoped
public class UserManager {


    private static SessionFactory factory;
    private Map<String, UserData> users = new HashMap<>();


    /**
     * Creates a new instance of UserManager
     */
    public UserManager() {
        try{
         factory = new Configuration().configure().buildSessionFactory();
      }catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
        createUserMap();
    }

    /**
     * Finds a user in the database using a username key
     * @return user if username is database, null otherwise
     */
    UserData find(String username) {
        return users.get(username);
    }

    /**
     * Adds user to the database
     */
    public void addUser(UserData user) {
        users.put(user.getUsername(), user);
        
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Prints a list of all the users in the database
     */
    public void userList() {
        for (String name : users.keySet()) {
            System.out.println(name);
        }
    }
    
    public void createUserMap() {
        Session session = factory.openSession();
        Query query = session.createQuery("from UserData");
        List<UserData> userList = query.list();
        for (UserData u : userList) {
            System.out.println(u.getUsername());
            
            users.put(u.getName(), u);
        }
        System.out.println(users.size());
    }
}
