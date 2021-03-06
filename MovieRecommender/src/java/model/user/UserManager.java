/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.event.ValueChangeEvent;
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
@ManagedBean(name = "userManager")
@ApplicationScoped
public class UserManager {

    private static SessionFactory factory;
    private Map<String, UserData> users = new HashMap<>();

    /**
     * Creates a new instance of UserManager
     */
    public UserManager() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        createUserMap();
    }

    /**
     * returns the User Map for Usermanager
     *
     * @return
     */
    Map<String, UserData> getMap() {
        return this.users;
    }

    /**
     * Finds a user in the database using a username key
     *
     * @return user if username is database, null otherwise
     */
    public UserData find(String username) {
        return users.get(username);
    }

    /**
     * Adds user to the database
     *
     * @param user
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
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * Prints a list of all the users in the database
     */
    public List<String> userList() {
        ArrayList<String> list = new ArrayList<String>();
        for (String name : users.keySet()) {
            list.add(name);
        }
        return list;
    }

    /**
     * Creates UserMap from the database on construction
     */
    public void createUserMap() {
        Session session = factory.openSession();
        Query query = session.createQuery("from UserData");
        List<UserData> userList = query.list();
        System.out.print(userList);
        for (UserData u : userList) {
            System.out.println(u.getUsername());
            System.out.println(u.getMajor());
            System.out.println(u.getAdmin());

            users.put(u.getName(), u);
        }
        System.out.println(users.size());
    }

    /**
     * Find if specific user is banned
     */
    public boolean isBanned(String username) {
        return users.get(username).getBanned();
    }

    /**
     * To be implemented
     */
    public void checkBoxListener(ValueChangeEvent event) {

    }

    /**
     * Flips the ban status of a single user.
     *
     */
    public void changeBan(String username) {
        Session session2 = factory.openSession();
        Query query;
        Transaction tx2 = null;
        try {
            tx2 = session2.beginTransaction();
            //String username = u.getUsername();

            if (!isBanned(username)) {
                String hql = "UPDATE UserData SET banned = true Where "
                        + "username = '" + username + "'";
                query = session2.createQuery(hql);
                //query.setParameter("banned", 1);
            } else {
                String hql = "UPDATE UserData SET banned = false Where "
                        + "username = '" + username + "'";
                query = session2.createQuery(hql);
                //query.setParameter("banned", 0);
            }
            int answer = query.executeUpdate();
            tx2.commit();
        } catch (Exception e) {
            if (tx2 != null) {
                tx2.rollback();
            }
            e.printStackTrace();
        } finally {
            session2.close();
        }
    }

}
