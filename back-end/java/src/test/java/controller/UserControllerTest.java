package controller;

import io.javalin.http.Handler;
import io.javalin.http.Context;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerTest {
    private Context ctx = mock(Context.class);

    //TODO
    //NOT WORKING
    @Test
    void testCheckLoginHandler() throws Exception {
        when(ctx.formParam("username")).thenReturn("test123");
        when(ctx.formParam("password")).thenReturn("test321");
        Handler checkLogin = UserController.checkLogin;
        checkLogin.handle(ctx);
    }
}