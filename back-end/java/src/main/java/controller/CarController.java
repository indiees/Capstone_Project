package controller;

import controller.util.Status;
import dao.CarDAO;
import dao.UserDAO;
import io.javalin.http.Handler;
import model.Car;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class CarController {
    public static Handler addCar = ctx ->{
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
        String cost_str;
        cost_str = ctx.formParam("cost");
        if (cost_str==null){
            ctx.json(new Status("No `cost` provided"));
            return;
        }
        int cost = Integer.parseInt(cost_str);

        String color;
        color = ctx.formParam("color");
        if (color==null){
            ctx.json(new Status("No `color` provided"));
            return;
        }

        String liscence_plate;
        liscence_plate = ctx.formParam("liscence_plate");
        if (liscence_plate==null){
            ctx.json(new Status("No `liscence_plate` provided"));
            return;
        }

        String make;
        make = ctx.formParam("make");
        if (make==null){
            ctx.json(new Status("No `make` provided"));
            return;
        }

        String year_str;
        year_str = ctx.formParam("year");
        if (year_str==null){
            ctx.json(new Status("No `year` provided"));
            return;
        }
        int year = Integer.parseInt(year_str);

        String bay_id_str;
        bay_id_str = ctx.formParam("bay_id");
        if (bay_id_str==null){
            ctx.json(new Status("No `bay_id` provided"));
            return;
        }
        int bay_id = Integer.parseInt(bay_id_str);
        
        CarDAO.createCar(cost, color, liscence_plate, make, year, bay_id);
        ctx.json(new Status());
    };
    public static Handler editCar = ctx ->{
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
        HashMap<String, String> props = new HashMap<String, String>();

        String str_car_id;
        str_car_id = ctx.formParam("car_id");
        if (str_car_id==null){
            ctx.json(new Status("No `car_id` Provided"));
            return;
        }
        int car_id = Integer.parseInt(str_car_id);

        String cost_str;
        cost_str = ctx.formParam("cost");
        if (cost_str!=null){
            props.put("cost",cost_str);
        }

        String color;
        color = ctx.formParam("color");
        if (color!=null){
            props.put("color",color);
        }

        String liscence_plate;
        liscence_plate = ctx.formParam("liscence_plate");
        if (liscence_plate!=null){
            props.put("liscence_plate",liscence_plate);
        }

        String make;
        make = ctx.formParam("make");
        if (make!=null){
            props.put("make",make);
        }

        String year;
        year = ctx.formParam("year");
        if (year!=null){
            props.put("year",year);
        }

        String bay_id;
        bay_id = ctx.formParam("bay_id");
        if (bay_id!=null){
            props.put("bay_id",bay_id);
        }


        if (CarDAO.updateCar(car_id, props)) {
            ctx.json(new Status());
            return;
        }
        ctx.json(new Status("An unexpected error has occurred"));

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
