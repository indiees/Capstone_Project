package dao;

import model.User;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class UserDAOTest {
    private User testUser;
    private boolean userExists = false;

    private static final String EMAIL = "UserDAO@test.com";
    private static final String PASS = "TestDAOUser";
    private static final String FNAME = "UserDAOTest.first.name";
    private static final String LNAME = "UserDAOTest.last.name";
    private static final String DUMMY = "testMissingValue";

    @BeforeEach
    public void setup() {
        if (!userExists) {
            testUser = UserDAO.createUser(EMAIL, PASS, FNAME, LNAME);
            userExists = (testUser != null);
        }
    }

    @Test
    public void testCreateUser() { Assertions.assertTrue(userExists); }

    @Test
    public void testRemoveUser() {
        Assumptions.assumeTrue(userExists);
        userExists = !UserDAO.removeUser(testUser.getUser_id());
        Assertions.assertFalse(userExists);
    }

    @Test
    public void testCheckLogin() {
        Assumptions.assumeTrue(userExists);
        User retrievedUser = UserDAO.checkLogin(EMAIL, PASS);
        Assertions.assertNotNull(retrievedUser);
        Assertions.assertEquals(testUser, retrievedUser);
    }

    @Test
    public void testCheckLoginFail() {
        Assumptions.assumeTrue(userExists);
        User retrievedUser = UserDAO.checkLogin(DUMMY, DUMMY);
        Assertions.assertNull(retrievedUser);
    }

    @Test
    public void testGetPersonByEmail() {
        Assumptions.assumeTrue(userExists);
        User retrievedUser = UserDAO.getPersonByEmail(EMAIL);
        Assertions.assertNotNull(retrievedUser);
        Assertions.assertEquals(testUser, retrievedUser);
    }

    @Test
    public void testGetPersonByEmailFail() {
        Assumptions.assumeTrue(userExists);
        User retrievedUser = UserDAO.getPersonByEmail(DUMMY);
        Assertions.assertNull(retrievedUser);
    }

    @Test
    public void testEmailInUse() {
        Assumptions.assumeTrue(userExists);
        boolean result = UserDAO.emailInUse(EMAIL);
        Assertions.assertTrue(result);
    }

    @Test
    public void testEmailInUseFail() {
        Assumptions.assumeTrue(userExists);
        boolean result = UserDAO.emailInUse(DUMMY);
        Assertions.assertFalse(result);
    }

    @AfterEach
    public void tearDown() {
        if (userExists) {
            userExists = !UserDAO.removeUser(testUser.getUser_id());
        }
    }
}
