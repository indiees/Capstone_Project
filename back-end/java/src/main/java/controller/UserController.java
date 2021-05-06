package controller;


import controller.util.Status;
import dao.UserDAO;
import io.javalin.http.Handler;
import model.User;

import javax.print.attribute.standard.NumberOfInterveningJobs;
import java.util.Objects;

public class UserController {
    public static Handler createUser = ctx ->{
        String str_email = ctx.formParam("email");
        if (str_email==null) {
            ctx.json(new Status("No 'email' Provided"));
            return;
        }
        if (UserDAO.emailInUse(str_email)){
            ctx.json(new Status("`email` is allready in use, please user another"));
            return;
        }
        String str_password = ctx.formParam("password");
        if (str_password==null) {
            ctx.json(new Status("No 'password' Provided"));
            return;
        }
        String str_first_name = ctx.formParam("first_name");
        if (str_first_name==null) {
            ctx.json(new Status("No 'first_name' Provided"));
            return;
        }
        String str_last_name = ctx.formParam("last_name");
        if (str_last_name==null) {
            ctx.json(new Status("No 'last_name' Provided"));
            return;
        }
        String str_account_type = ctx.formParam("account_type");
        if (str_account_type==null) {
            str_account_type = "1";
        }
        int account_type = Integer.parseInt(str_account_type);

        User user = UserDAO.createUser(str_email, str_password, str_first_name, str_last_name, account_type);
        System.out.println(user);
        if (user == null){
            ctx.json(new Status("Cannot create account (Likely a database connection issue)"));
            return;
        }
        ctx.json(new Status());
    };

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

