package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

public class BookingTest {
    private static final int BOOKINGID = 0;
    private static final int CARID = 0;
    private static final int USERID = 0;
    private static final int STARTBAYID = 0;
    private static final int ENDBAYID = 0;
    private static final int DURATION = 10;
    private static final int DUMMYINT = -1;
    private static final double RATE = 1.1;
    private static final double DUMMYRATE = 0.1;
    private Timestamp ts;

    @BeforeEach
    public void setup() {
        ts = new Timestamp((System.currentTimeMillis() / 1000) * 1000);
    }

    @Test
    public void testCreateBooking() {
        Booking testBooking = new Booking(BOOKINGID, CARID, USERID, STARTBAYID, ENDBAYID, DURATION, RATE, ts);
    }

    @Test
    public void testToString() {
        Booking testBooking = new Booking(BOOKINGID, CARID, USERID, STARTBAYID, ENDBAYID, DURATION, RATE, ts);
        testBooking.toString();
    }

    @Test
    public void testEqualsPositive() {
        Booking testBooking = new Booking(BOOKINGID, CARID, USERID, STARTBAYID, ENDBAYID, DURATION, RATE, ts);
        Booking identicalBooking = new Booking(BOOKINGID, CARID, USERID, STARTBAYID, ENDBAYID, DURATION, RATE, ts);
        Assertions.assertEquals(testBooking, identicalBooking);
    }

    @Test
    public void testEqualsNegative() {
        Booking testBooking = new Booking(BOOKINGID, CARID, USERID, STARTBAYID, ENDBAYID, DURATION, RATE, ts);
        Booking diffBooking = new Booking(DUMMYINT, DUMMYINT, DUMMYINT, DUMMYINT, DUMMYINT, DUMMYINT, DUMMYRATE, ts);
        Assertions.assertNotEquals(testBooking, diffBooking);
    }
}