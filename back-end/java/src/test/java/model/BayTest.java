package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BayTest {
    private static final int BAYID = 0;
    private static final String LOCATION = "testLocation";
    private static final int MAX_CAP = 10;
    private static final int DUMMYINT = -1;
    private static final String DUMMYSTR = "dummy";

    @Test
    public void testCreateBay() {
        Bay testBay = new Bay(BAYID, LOCATION, MAX_CAP);
    }

    @Test
    public void testToString() {
        Bay testBay = new Bay(BAYID, LOCATION, MAX_CAP);
        String result = testBay.toString();
    }

    @Test
    public void testEqualsPositive() {
        Bay testBay = new Bay(BAYID, LOCATION, MAX_CAP);
        Bay identicalBay = new Bay(BAYID, LOCATION, MAX_CAP);
        Assertions.assertEquals(testBay, identicalBay);
    }

    @Test
    public void testEqualsNegative() {
        Bay testBay = new Bay(BAYID, LOCATION, MAX_CAP);
        Bay diffBay = new Bay(DUMMYINT, DUMMYSTR, DUMMYINT);
        Assertions.assertNotEquals(testBay, diffBay);
    }
}