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
}
