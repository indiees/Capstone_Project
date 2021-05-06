package controller;

import controller.util.Status;
import dao.BayDAO;
import dao.UserDAO;
import io.javalin.http.Handler;
import model.Bay;
import model.User;

import java.util.ArrayList;

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
        ctx.json(new Status("Not yet Implemented"));
        return;
    };
}
