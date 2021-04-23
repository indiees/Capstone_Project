package controller.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StatusTest {
    @Test
    public void testToString_givenMessage() {
        String status = "test";
        String message = "message";
        Status testStatus = new Status(status, message);
        String result = testStatus.toString();
    }

    @Test
    public void testToString_givenPayload() {
        Object payload = new String("payload");
        Status testStatus = new Status(payload);
        String result = testStatus.toString();
    }

    @Test
    public void testEquals_givenMessage(){
        String status = "test";
        String message = "message";
        Status s1 = new Status(status, message);
        Status s2 = new Status(status, message);
        Assertions.assertTrue(s1.equals(s2));
    }

    @Test
    public void testEquals_givenPayload(){
        Object payload1 = new String("payload");
        Object payload2 = new String("payload");
        Status s1 = new Status(payload1);
        Status s2 = new Status(payload2);
        Assertions.assertTrue(s1.equals(s2));
    }
}