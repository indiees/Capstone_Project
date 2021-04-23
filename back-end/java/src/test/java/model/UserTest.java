package model;

import controller.util.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {
    private static final int USERID = 0;
    private static final String EMAIL = "User@test.com";
    private static final String PASS = "TestUser";
    private static final String FNAME = "UserTest.first.name";
    private static final String LNAME = "UserTest.last.name";
    private static final String DUMMY = "testDummyValue";
    private static final int DUMMYID = -1;

    @Test
    public void testCreateUser() {
        String hashedPass = Utils.generateHashPassword(PASS);
        User testUser = new User(USERID, EMAIL, hashedPass, FNAME, LNAME);
        Assertions.assertEquals(USERID, testUser.getUser_id());
        Assertions.assertEquals(EMAIL, testUser.getEmail());
        Assertions.assertEquals(FNAME, testUser.getFirst_name());
        Assertions.assertEquals(LNAME, testUser.getLast_name());
    }

    @Test
    public void testToString() {
        String hashedPass = Utils.generateHashPassword(PASS);
        User testUser = new User(USERID, EMAIL, hashedPass, FNAME, LNAME);
        String result = testUser.toString();
    }

    @Test
    public void testEqualsPositive() {
        String hashedPass = Utils.generateHashPassword(PASS);
        User testUser = new User(USERID, EMAIL, hashedPass, FNAME, LNAME);
        User identicalUser = new User(USERID, EMAIL, hashedPass, FNAME, LNAME);
        Assertions.assertEquals(testUser, identicalUser);
    }

    @Test
    public void testEqualsNegative() {
        String hashedPass = Utils.generateHashPassword(PASS);
        User testUser = new User(USERID, EMAIL, hashedPass, FNAME, LNAME);
        User diffUser = new User(DUMMYID, DUMMY, DUMMY, DUMMY, DUMMY);
        Assertions.assertNotEquals(testUser, diffUser);
    }
}
