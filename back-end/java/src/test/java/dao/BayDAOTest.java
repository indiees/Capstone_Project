package dao;

import model.Bay;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class BayDAOTest {
    private Bay testBay;
    private boolean bayExists = false;

    private static final String LOCATION = "testLocation";
    private static final int MAX_CAP = 10;
    private static final int DUMMYINT = -1;

    @BeforeEach
    public void setup() {
        if (!bayExists) {
            testBay = BayDAO.createBay(LOCATION, MAX_CAP);
            bayExists = (testBay != null);
        }
    }

    @Test
    public void testCreateBay() { Assertions.assertTrue(bayExists); }

    @Test
    public void testGetBays() {
        ArrayList<Bay> retrievedBays = BayDAO.getBays();
        Assertions.assertNotNull(retrievedBays);
    }

    @Test
    public void testGetBayPositive() {
        Assumptions.assumeTrue(bayExists);
        Bay retrievedBay = BayDAO.getBay(testBay.getBay_id());
        Assertions.assertNotNull(retrievedBay);
        Assertions.assertEquals(testBay, retrievedBay);
    }

    @Test
    public void testGetBayNegative() {
        Assumptions.assumeTrue(bayExists);
        Bay retrievedBay = BayDAO.getBay(DUMMYINT);
        Assertions.assertNull(retrievedBay);
    }

    @Test
    public void testRemoveBay() {
        Assumptions.assumeTrue(bayExists);
        bayExists = !BayDAO.removeBay(testBay.getBay_id());
        Assertions.assertFalse(bayExists);
    }

    @AfterEach
    public void tearDown() {
        if (bayExists) {
            bayExists = !BayDAO.removeBay(testBay.getBay_id());
        }
    }
}