package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CarTest {
    private static final int CARID = 0;
    private static final double COST = 10.1;
    private static final String COLOUR = "Brown";
    private static final String LP = "LET137";
    private static final String MAKE = "Ford";
    private static final int YEAR = 2000;
    private static final int BAYID = 0;
    private static final int DUMMYINT = -1;
    private static final double DUMMYCOST = 1.1;
    private static final String DUMMY = "dummy";

    @Test
    public void testCreateCar() {
        Car testCar = new Car(CARID, COST, COLOUR, LP, MAKE, YEAR, BAYID);
    }

    @Test
    public void testToString() {
        Car testCar = new Car(CARID, COST, COLOUR, LP, MAKE, YEAR, BAYID);
        String result = testCar.toString();
    }

    @Test
    public void testEqualsPositive() {
        Car testCar = new Car(CARID, COST, COLOUR, LP, MAKE, YEAR, BAYID);
        Car identicalCar = new Car(CARID, COST, COLOUR, LP, MAKE, YEAR, BAYID);
        Assertions.assertEquals(testCar, identicalCar);
    }

    @Test
    public void testEqualsNegative() {
        Car testCar = new Car(CARID, COST, COLOUR, LP, MAKE, YEAR, BAYID);
        Car diffCar = new Car(DUMMYINT, DUMMYCOST, DUMMY, DUMMY, DUMMY, DUMMYINT, DUMMYINT);
        Assertions.assertNotEquals(testCar, diffCar);
    }
}