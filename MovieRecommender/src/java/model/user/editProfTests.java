package org.eclipse.e4.core.internal.tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import model.user.User;
import model.user.UserData;
import model.user.UserManager;

public class editProfTests {
    User u;
    UserManager um;
    UserData ud;

    public editProfTests() {
    }

    @Before
    public void setUp() {
        u = new User();
        um = new UserManager();
        um.addUser(new UserData("test", "test"));
        u.setMajor(0);
        u.setName("test");
        u.setPassword("test");
    }

    /*
     * Test name length being zero
     */
    @Test
    public void nameLengthZero() {
        u.setName("");
        assertNull(u.editProf());
    }

    /*
     * Test password length being zero
     */
    @Test
    public void passLengthZero() {
        u.setPassword("");
        assertNull(u.editProf());
    }

    /*
     * Test name being null
     */
    @Test
    public void nameNull() {
        u.setName(null);
        assertNull(u.editProf());
    }

    /*
     * Test password being null
     */
    @Test
    public void passNull() {
        u.setPassword(null);
        assertNull(u.editProf());
    }

    /*
     * Test editProf with no values changed
     */
    @Test
    public void sameNamePassMajor() {
        assertEquals("ViewProfile", u.editProf());
        assertEquals("test", u.getName());
        assertEquals("test", u.getPassword());
        assertEquals(0, u.getMajor());
        ud = um.find("test");
        assertEquals("test", ud.getName());
        assertEquals("test", ud.getPassword());
        assertEquals(0, ud.getMajor());
    }

    /*
     * Test editProf with name changed
     */
    @Test
    public void differentName() {
        u.setName("testName");
        assertEquals("ViewProfile", u.editProf());
        assertEquals("testName", u.getName());
        assertEquals("test", u.getPassword());
        assertEquals(0, u.getMajor());
        ud = um.find("test");
        assertEquals("testName", ud.getName());
        assertEquals("test", ud.getPassword());
        assertEquals(0, ud.getMajor());
    }

    /*
     * Test editProf with password changed
     */
    @Test
    public void differentPass() {
        u.setPassword("testPass");
        assertEquals("ViewProfile", u.editProf());
        assertEquals("test", u.getName());
        assertEquals("testPass", u.getPassword());
        assertEquals(0, u.getMajor());
        ud = um.find("test");
        assertEquals("test", ud.getName());
        assertEquals("testPass", ud.getPassword());
        assertEquals(0, ud.getMajor());
    }

    /*
     * Test editProf with major changed
     */
    @Test
    public void differentMajor() {
        u.setMajor(3);
        assertEquals("ViewProfile", u.editProf());
        assertEquals("test", u.getName());
        assertEquals("testPass", u.getPassword());
        assertEquals(3, u.getMajor());
        ud = um.find("test");
        assertEquals("test", ud.getName());
        assertEquals("test", ud.getPassword());
        assertEquals(3, ud.getMajor());
    }
}
