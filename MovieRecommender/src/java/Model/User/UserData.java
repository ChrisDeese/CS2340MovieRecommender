/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.User;

/**
 *
 * @author chris
 * 
 * This class to be deleted once database is implemented.
 */
public class UserData {

    
    private String username;
    private String password;
    //more to be added later
    
    UserData(String name, String pass) {
        this.username = name;
        this.password = pass;
    }
    
    boolean checkP(String p) {
        return (p.equals(password));
    }
    
}
