package dao;

import model.Booking;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

public class BookingDAOTest {
    private Booking testBooking;
    private boolean bookingExists = false;

    private static final int CARID = 0;
    private static final int USERID = 0;
    private static final int STARTBAYID = 0;
    private static final int ENDBAYID = 0;
    private static final int DURATION = 10;
    private static final int DUMMYINT = -1;
    private static final double RATE = 1.1;
    private Timestamp ts;

    @BeforeEach
    public void setup() {
        if (!bookingExists) {
            Date date = new Date();
            ts = new Timestamp(date.getTime());
            testBooking = BookingDAO.createBooking(CARID, USERID, STARTBAYID, ENDBAYID, ts, DURATION, RATE);
            bookingExists = (testBooking != null);
        }
    }

    @Test
    public void testCreateBooking() { Assertions.assertTrue(bookingExists); }

    @Test
    public void testGetBookingsByCarPositive() {
        Assumptions.assumeTrue(bookingExists);
        ArrayList<Booking> retrievedBooking = BookingDAO.getBookingsByCar(CARID);
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
//        ArrayList<Booking> retrievedBooking = BookingDAO.getBookingsByUser(USERID);
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
        bookingExists = !BookingDAO.removeBooking(testBooking.getBooking_id(), USERID);
        Assertions.assertFalse(bookingExists);
    }

    @AfterEach
    public void tearDown() {
        if (bookingExists) {
            bookingExists = !BookingDAO.removeBooking(testBooking.getBooking_id(), USERID);
        }
    }
}