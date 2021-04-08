package controller;

import controller.util.Status;
import io.javalin.http.Context;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

//TODO
//Fix all of this
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    private Context ctx;

    private static final String VALIDUSER = "test123@gmail.com";
    private static final String VALIDPASS = "test321";
    private static final String INVALIDUSER = "-1";
    private static final String INVALIDPASS = "-1";

    @BeforeEach
    public void setup() {
        ctx = mock(Context.class);
    }

    @Test
    public void testValidLoginCredentials() throws Exception {
        // Set up the mock context with valid credentials
        when(ctx.formParam("username")).thenReturn(VALIDUSER);
        when(ctx.formParam("password")).thenReturn(VALIDPASS);
        UserController.checkLogin.handle(ctx);
        // Use argumentCaptor to get the status
        ArgumentCaptor<Status> statusCaptor = ArgumentCaptor.forClass(Status.class);
        // Use verify to capture the status passed to ctx.json()
        verify(ctx).json(statusCaptor.capture());
        // Check the status
        Assertions.assertEquals("success", statusCaptor.getValue().status);
    }

    @Test
    public void testInvalidLoginCredentials() throws Exception {
        // Set up the mock context with invalid credentials
        when(ctx.formParam("username")).thenReturn(INVALIDUSER);
        when(ctx.formParam("password")).thenReturn(INVALIDPASS);
        UserController.checkLogin.handle(ctx);
        // Use argumentCaptor to get the status
        ArgumentCaptor<Status> statusCaptor = ArgumentCaptor.forClass(Status.class);
        // Use verify to capture the status passed to ctx.json()
        verify(ctx).json(statusCaptor.capture());
        // Check the status
        Assertions.assertEquals("failed", statusCaptor.getValue().status);
    }
}