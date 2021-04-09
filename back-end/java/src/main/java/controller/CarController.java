package controller;

import controller.util.Status;
import dao.CarDAO;
import io.javalin.http.Handler;
import model.Car;

import java.util.ArrayList;

public class CarController {
    public static Handler getCars = ctx ->{
        ArrayList<Car> cars = CarDAO.getCars();
        if (cars!=null){
            ctx.json(new Status(cars));
            return;
        }
        ctx.json(new Status("No cars with those details can be found"));
        return;
    };
}
