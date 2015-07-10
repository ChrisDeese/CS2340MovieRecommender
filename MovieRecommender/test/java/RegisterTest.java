package java;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import model.user.User;
import model.user.UserData;
import model.user.UserManager;
import org.junit.Assert;

/**
 *
 * @author Toni Johnson
 */
public class RegisterTest {

    private UserData uData;
    private User user;

    public RegisterTest() {
    }

    @Before
    public void setUp() {
        user = new User();

    }

    @Test
    //Test normal registration
    public void testNewUser() {
        user.setUsername("test1");
        user.setPassword("pass1");
        user.setName("Junit");

        assertEquals("Should print register success!", user.register());
    }

    //tests if when the username has already been registered

    @Test
    public void testAlreadyUsedUname() {
        user.setUsername("test3");
        user.setPassword("pass3");
        user.setName("Junit");
        user.register();
        User user1 = new User();
        user1.setUsername("test3");
        user1.setPassword("pass3");
        user1.setName("Junit");
        assertEquals("Should return username already taken and not register. ",
                user1.register());

    }

    //test of registration with empty name
    @Test
    public void testNoName() {
        user.setUsername("dog");
        user.setPassword("pass1");
        user.setName("");
        assertEquals("Should return register success!",
                user.register());
    }

    //test with no password
    @Test
    public void testNoPassword() {
        user.setUsername("test2");
        user.setPassword("");
        user.setName("Junit");
        assertEquals("Should return password empty and not register",
                user.register());

    }

    //test with both username and password empty
    @Test
    public void testBothfieldsEmpty() {
        user.setUsername("");
        user.setPassword("");
        user.setName("Junit");
        assertEquals("Should return password and username empty and "
                + "not register",
                user.register());

    }

}
