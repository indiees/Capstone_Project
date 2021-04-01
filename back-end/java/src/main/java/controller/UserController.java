package controller;


import controller.util.Status;
import dao.UserDAO;
import io.javalin.http.Handler;
import model.User;

public class UserController {
    public static Handler checkLogin = ctx ->{
        String str_email = ctx.formParam("email");
        if (str_email==null) {
            ctx.json(new Status("No 'email' Provided"));
            return;
        }
        String str_password = ctx.formParam("password");
        if (str_password==null) {
            ctx.json(new Status("No 'password' Provided"));
            return;
        }
        User user = UserDAO.checkLogin(str_email, str_password);
        if (user!=null){
            ctx.json(new Status(user));
        }
        else{
            ctx.json(new Status("Incorrect username or password"));
        }
        return;

    };
}

