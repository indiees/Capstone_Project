package controller;

import controller.util.Status;
import dao.BayDAO;
import dao.UserDAO;
import io.javalin.http.Handler;
import model.Bay;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class BayController {
    public static Handler getBays = ctx ->{
        ArrayList<Bay> bays = BayDAO.getBays();
        if (bays!=null){
            ctx.json(new Status(bays));
            return;
        }
        ctx.json(new Status("No bays with those details can be found"));
        return;
    };

    public static Handler addBay = ctx ->{

        //Authentication
        String loginEmail = ctx.formParam("loginEmail");
        if (loginEmail==null){
            ctx.json(new Status("No `loginEmail` Provided (The email of the user making the changes)"));
            return;
        }
        String loginPassword = ctx.formParam("loginPassword");
        if (loginPassword==null){
            ctx.json(new Status("No `loginPassword` Provided (The password of the user making the changes)"));
            return;
        }
        User user = UserDAO.checkLogin(loginEmail, loginPassword);
        if (user==null){
            ctx.json(new Status("Incorrect authentication provided"));
            return;
        }
        //needs to be a seperate check otherwise nullpointerexception
        if (user.getAccount_type()<2){
            ctx.json(new Status("Incorrect Authentication provided"));
            return;
        }

        String location;
        location = ctx.formParam("location");
        if (location==null){
            ctx.json(new Status("No `location` provided"));
            return;
        }

        String max_capacity_str;
        max_capacity_str = ctx.formParam("max_capacity");
        if (max_capacity_str==null){
            ctx.json(new Status("No `max_capacity` provided"));
            return;
        }
        int max_cap = Integer.parseInt(max_capacity_str);
        BayDAO.createBay(location, max_cap);

        ctx.json(new Status());
        return;
    };

    public static Handler editBay = ctx ->{
        //Authentication
        String loginEmail = ctx.formParam("loginEmail");
        if (loginEmail==null){
            ctx.json(new Status("No `loginEmail` Provided (The email of the user making the changes)"));
            return;
        }
        String loginPassword = ctx.formParam("loginPassword");
        if (loginPassword==null){
            ctx.json(new Status("No `loginPassword` Provided (The password of the user making the changes)"));
            return;
        }
        User user = UserDAO.checkLogin(loginEmail, loginPassword);
        if (user==null){
            ctx.json(new Status("Incorrect authentication provided"));
            return;
        }
        //needs to be a seperate check otherwise nullpointerexception
        if (user.getAccount_type()<2){
            ctx.json(new Status("Incorrect Authentication provided"));
            return;
        }
        HashMap<String, String> props = new HashMap<String, String>();
        String str_bay_id;
        str_bay_id = ctx.formParam("bay_id");
        if (str_bay_id==null){
            ctx.json(new Status("No `bay_id` Provided"));
            return;
        }
        int bay_id = Integer.parseInt(str_bay_id);

        String location;
        location = ctx.formParam("location");
        if (location!=null){
            props.put("location",location);
        }

        String max_capacity_str;
        max_capacity_str = ctx.formParam("max_capacity");
        if (max_capacity_str!=null){
            props.put("max_capactiy",max_capacity_str);
        }

        if (BayDAO.editBay(bay_id, props)){
            ctx.json(new Status());
            return;
        }

        ctx.json(new Status("An unexpected error has occurred"));
        return;
    };
}
