/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.User;

import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

/**
 *
 * @author chris
 */
@ManagedBean (name = "userManager")
@ApplicationScoped
public class UserManager {


    private Map<String, UserData> users = new HashMap<>();


    /**
     * Creates a new instance of UserManager
     */
    public UserManager() {
        users.put("user", new UserData("user", "pass"));
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
    }

    /**
     * Prints a list of all the users in the database
     */
    public void userList() {
        for (String name : users.keySet()) {
            System.out.println(name);
        }
    }
}
