package org.eclipse.e4.core.internal.tests;

import static org.junit.Assert.*;
import org.junit.Test;

public class editProfTests {
    User u;
    UserManager um;
    UserData ud;

    public editProfTests() {
        u = new User();
        um = new UserManager();
        um.addUser(new UserData("test", "test"));
        ud = um.find("test");
        u.setMajor(0);
        u.setName("test");
        u.setPassword("test");
    }

    @Test
    public void nameLengthZero() {
        u.setName("");
        assertNull(u.editProf());
    }

    @Test
    public void passLengthZero() {
        u.setPassword("");
        assertNull(u.editProf());
    }

    @Test
    public void nameNull() {
        u.setName(null);
        assertNull(u.editProf());
    }

    @Test
    public void passNull() {
        u.setPassword(null);
        assertNull(u.editProf());
    }

    @Test
    public void sameNamePassMajor() {
        assertEquals("ViewProfile", u.editProf());
        assertEquals("test", u.getName());
        assertEquals("test", u.getPassword());
        assertEquals(0, u.getMajor());
        assertEquals("test", ud.getName());
        assertEquals("test", ud.getPassword());
        assertEquals(0, ud.getMajor());
    }

    @Test
    public void differentName() {
        u.setName("testName");
        assertEquals("ViewProfile", u.editProf());
        assertEquals("testName", u.getName());
        assertEquals("test", u.getPassword());
        assertEquals(0, u.getMajor());
        assertEquals("testName", ud.getName());
        assertEquals("test", ud.getPassword());
        assertEquals(0, ud.getMajor());
    }

    @Test
    public void differentPass() {
        u.setPassword("testPass");
        assertEquals("ViewProfile", u.editProf());
        assertEquals("test", u.getName());
        assertEquals("testPass", u.getPassword());
        assertEquals(0, u.getMajor());
        assertEquals("test", ud.getName());
        assertEquals("testPass", ud.getPassword());
        assertEquals(0, ud.getMajor());
    }

    @Test
    public void differentMajor() {
        u.setMajor(3);
        assertEquals("ViewProfile", u.editProf());
        assertEquals("test", u.getName());
        assertEquals("testPass", u.getPassword());
        assertEquals(3, u.getMajor());
        assertEquals("test", ud.getName());
        assertEquals("test", ud.getPassword());
        assertEquals(3, ud.getMajor());
    }
}
