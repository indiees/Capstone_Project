package controller.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilsTest {
    @Test
    public void testPasswordValidation() {
        String password = "123example";
        String hash = Utils.generateHashPassword(password);
        Assertions.assertTrue(Utils.passwordIsValid(password, hash));
    }
}