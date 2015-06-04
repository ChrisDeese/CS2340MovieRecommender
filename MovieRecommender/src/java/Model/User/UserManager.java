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
    
    UserData find(String username) {
        return users.get(username);
    }
    
}