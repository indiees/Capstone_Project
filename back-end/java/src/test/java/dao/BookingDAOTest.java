package dao;

import model.Bay;
import model.Booking;
import model.Car;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Timestamp;
import java.util.ArrayList;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookingDAOTest {
    private Booking testBooking;
    private boolean bookingExists = false;

    private static final int DUMMYINT = -1;
    private static final double RATE = 1.1;

    private static final String LOCATION = "testLocation";
    private static final int MAX_CAP = 10;
    private static final double COST = 10.1;
    private static final String COLOUR = "Brown";
    private static final String LP = "LET137";
    private static final String MAKE = "Ford";
    private static final int YEAR = 2000;
    private static final String EMAIL = "UserDAO@test.com";
    private static final String PASS = "TestDAOUser";
    private static final String FNAME = "UserDAOTest.first.name";
    private static final String LNAME = "UserDAOTest.last.name";
    private static final int ACCTYPE = 1;

    private Bay testBay;
    private Car testCar;
    private User testUser;

    @BeforeAll
    public void setUp() {
        testBay = BayDAO.createBay(LOCATION, MAX_CAP);
        testCar = CarDAO.createCar(COST, COLOUR, LP, MAKE, YEAR, testBay.getBay_id());
        testUser = UserDAO.createUser(EMAIL, PASS, FNAME, LNAME, ACCTYPE);
    }

    @BeforeEach
    public void setup() {
        if (!bookingExists) {
            Timestamp ts = new Timestamp((System.currentTimeMillis() / 1000) * 1000);
            int bay_id = testBay.getBay_id();
            testBooking = BookingDAO.createBooking(testCar.getCar_id(), testUser.getUser_id(),
                    bay_id, ts, RATE);
            bookingExists = (testBooking != null);
        }
    }

    @Test
    public void testCreateBooking() { Assertions.assertTrue(bookingExists); }

    @Test
    public void testGetBookingsByCarPositive() {
        Assumptions.assumeTrue(bookingExists);
        ArrayList<Booking> retrievedBooking = BookingDAO.getBookingsByCar(testCar.getCar_id());
        System.out.println(retrievedBooking.get(0).toString());
        System.out.println(testBooking.toString());
        System.out.println(testBooking.getDate());
        Assertions.assertNotNull(retrievedBooking);
        Assertions.assertTrue(retrievedBooking.contains(testBooking));
    }

    @Test
    public void testGetBookingsByCarNegative() {
        Assumptions.assumeTrue(bookingExists);
        ArrayList<Booking> retrievedBooking = BookingDAO.getBookingsByCar(DUMMYINT);
        Assertions.assertNull(retrievedBooking);
    }

    // TODO
    // Strange parameters, difficult to test
//    @Test
//    public void testGetBookingsByUserPositive() {
//        Assumptions.assumeTrue(bookingExists);
//        ArrayList<Booking> retrievedBooking = BookingDAO.getBookingsByUser(testUser.getUser_id());
//        Assertions.assertNotNull(retrievedBooking);
//        Assertions.assertTrue(retrievedBooking.contains(testBooking));
//    }
//
//    @Test
//    public void testGetBookingsByUserNegative() {
//        Assumptions.assumeTrue(bookingExists);
//        ArrayList<Booking> retrievedBooking = BookingDAO.getBookingsByUser(DUMMYINT);
//        Assertions.assertNull(retrievedBooking);
//    }

    @Test
    public void testRemoveBooking() {
        Assumptions.assumeTrue(bookingExists);
        // Seems dumb to retrieve the user id here. Should look into it
        bookingExists = !BookingDAO.removeBooking(testBooking.getBooking_id(), testUser.getUser_id());
        Assertions.assertFalse(bookingExists);
    }

    @AfterEach
    public void teardown() {
        if (bookingExists) {
            bookingExists = !BookingDAO.removeBooking(testBooking.getBooking_id(), testUser.getUser_id());
        }
    }

    @AfterAll
    public void tearDown() {
        CarDAO.removeCar(testCar.getCar_id());
        BayDAO.removeBay(testBay.getBay_id());
        UserDAO.removeUser(testUser.getUser_id());
    }
}