package controller;


import controller.util.Status;
import io.javalin.http.Handler;

public class UserController {
    public static Handler checkLogin = ctx ->{
        String str_username = ctx.formParam("username");
        if (str_username==null) {
            ctx.json(new Status("No 'username' Provided"));
            return;
        }
        String str_password = ctx.formParam("password");
        if (str_password==null) {
            ctx.json(new Status("No 'password' Provided"));
            return;
        }
        //For now the username and password are hard coded, but we will integrate this with database at a later date
        if (str_username.equals("test123") && str_password.equals("test321")){
            ctx.json(new Status("success", "Login success"));
        }
        else{
            ctx.json(new Status("Incorrect username or password"));
        }

    };
}

