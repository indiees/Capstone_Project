package dao;

import model.Bay;
import model.Car;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashMap;

public class CarDAOTest {
    private Car testCar;
    private boolean carExists = false;

    private static final double COST = 10.1;
    private static final String COLOUR = "Brown";
    private static final String LP = "LET137";
    private static final String MAKE = "Ford";
    private static final int YEAR = 2000;
    private static final int DUMMYINT = -1;
    private static final double DUMMYCOST = 1.1;
    private static final String DUMMY = "dummy";
    private static final String LOCATION = "testLocation";
    private static final int MAX_CAP = 10;

    private Bay testBay;

    @BeforeAll
    public void setUp() {
        testBay = BayDAO.createBay(LOCATION, MAX_CAP);
    }

    @BeforeEach
    public void setup() {
        if (!carExists) {
            testCar = CarDAO.createCar(COST, COLOUR, LP, MAKE, YEAR, testBay.getBay_id());
            carExists = (testCar != null);
        }
    }

    @Test
    public void testCreateCar() { Assertions.assertTrue(carExists); }

    @Test
    public void testGetCars() {
        // No need to assume that a car was added
        // This method should function regardless
        ArrayList<Car> retrievedCars = CarDAO.getCars();
        Assertions.assertNotNull(retrievedCars);
    }

    @Test
    public void testGetCarPositive() {
        Assumptions.assumeTrue(carExists);
        Car retrievedCar = CarDAO.getCar(testCar.getCar_id());
        Assertions.assertNotNull(retrievedCar);
        Assertions.assertEquals(testCar, retrievedCar);
    }

    @Test
    public void testGetCarNegative() {
        Assumptions.assumeTrue(carExists);
        Car retrievedCar = CarDAO.getCar(DUMMYINT);
        Assertions.assertNull(retrievedCar);
    }

    // TODO
    // Blackbox testing would be more effective here. Work with the hashmaps.

    @Test
    public void testGetLocations() {
        // No need to assume that a car was added
        ArrayList<String> retrievedLocations = CarDAO.getLocations();
        Assertions.assertNotNull(retrievedLocations);
    }

    @Test
    public void testGetColors() {
        // No need to assume that a car was added
        ArrayList<String> retrievedColors = CarDAO.getColors();
        Assertions.assertNotNull(retrievedColors);
    }

    @Test
    public void testGetMakes() {
        // No need to assume that a car was added
        ArrayList<String> retrievedMakes = CarDAO.getMakes();
        Assertions.assertNotNull(retrievedMakes);
    }

    @Test
    public void testRemoveCar() {
        Assumptions.assumeTrue(carExists);
        carExists = !CarDAO.removeCar(testCar.getCar_id());
        Assertions.assertFalse(carExists);
    }

    @AfterEach
    public void teardown() {
        if (carExists) {
            carExists = !CarDAO.removeCar(testCar.getCar_id());
        }
    }

    @AfterAll
    public void tearDown() {
        BayDAO.removeBay(testBay.getBay_id());
    }
}