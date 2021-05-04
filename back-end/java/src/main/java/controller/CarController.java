package controller;

import controller.util.Status;
import dao.CarDAO;
import io.javalin.http.Handler;
import model.Car;

import java.util.ArrayList;
import java.util.HashMap;

public class CarController {
    public static Handler addCar = ctx ->{

    };
    public static Handler editCar = ctx ->{

    };

    public static Handler getCars = ctx ->{
        HashMap<String, String> props = new HashMap<String, String>();

        String str_car_id;
        str_car_id = ctx.queryParam("car_id");
        if (str_car_id!=null){
            props.put("car_id",str_car_id);
        }

        String str_cost_min;
        str_cost_min = ctx.queryParam("cost_min");
        if (str_cost_min!=null){
            props.put("cost_min",str_cost_min);
        }

        String str_cost_max;
        str_cost_max = ctx.queryParam("cost_max");
        if (str_cost_max!=null){
            props.put("cost_max",str_cost_max);
        }

        String str_color;
        str_color = ctx.queryParam("color");
        if (str_color!=null){
            props.put("color",str_color);
        }

        String str_make;
        str_make = ctx.queryParam("make");
        if (str_make!=null){
            props.put("make",str_make);
        }

        String str_year_min;
        str_year_min = ctx.queryParam("year_min");
        if (str_year_min!=null){
            props.put("year_min",str_year_min);
        }

        String str_year_max;
        str_year_max = ctx.queryParam("year_max");
        if (str_year_max!=null){
            props.put("year_max",str_year_max);
        }

        ArrayList<Car> cars = CarDAO.getCars(props);
        if (cars!=null){
            ctx.json(new Status(cars));
            return;
        }
        ctx.json(new Status("No cars with those details can be found"));
        return;
    };
    public static Handler getCarLocations = ctx ->{
        ArrayList<String> locations = CarDAO.getLocations();
        if (locations!=null){
            ctx.json(new Status(locations));
            return;
        }
        ctx.json(new Status("Failure to connect to server"));
        return;
    };
    public static Handler getCarColors = ctx ->{
        ArrayList<String> colors = CarDAO.getColors();
        if (colors!=null){
            ctx.json(new Status(colors));
            return;
        }
        ctx.json(new Status("Failure to connect to server"));
        return;
    };
    public static Handler getCarMakes = ctx ->{
        ArrayList<String> makes = CarDAO.getMakes();
        if (makes!=null){
            ctx.json(new Status(makes));
            return;
        }
        ctx.json(new Status("Failure to connect to server"));
        return;
    };
}
