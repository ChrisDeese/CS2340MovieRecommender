package java;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import model.user.User;
import model.user.UserData;
import model.user.UserManager;

/**
 *
 * @author Prit Patel
 */

public class LoginTest{

    private User user;
    private UserData ud;
    private UserManager um;

    public LoginTest() {

    }

    @Before
    public void createProfile() {
        user = new User();
        um = new UserManager();
        ud = new UserData("SampleUser", "SamplePass");
        um.addUser(ud);
    }

    // Checks if the user can login with the wrong username.
    @Test
    public void wrongUser() {
        assertEquals("index.xhtml?faces-redirect=true",user.login());
        ud.setUsername("WrongUser");
        assertNull(user.login());

    }

    // Checks if the user can login without a username.
    @Test
    public void nullUser() {
      // ud.setUsername("SampleUser");
      // assertEquals("index.xhtml?faces-redirect=true",user.login());
      ud.setUsername("");
      assertNull(user.login());

    }

    // Checks if the user can login with the wrong password.
    @Test
    public void wrongPass() {
        ud.setUsername("SampleUser");
        ud.setPassword("WrongPass");
        assertNull(user.login());
    }

    // Checks if the user can login without a password.
    @Test
    public void nullPass() {
        ud.setPassword("");
        ud.setUsername("SampleUser");
        assertNull(user.login());

    }

    // Checks if the user can login with the correct username and password.
    @Test
    public void rightCheck() {
        ud.setPassword("SampleUser");
        ud.setUsername("SamplePass");
        assertEquals("index.xhtml?faces-redirect=true",user.login());
    }
}
