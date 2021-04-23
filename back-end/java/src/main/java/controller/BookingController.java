package controller;

import controller.util.Status;
import dao.BookingDAO;
import dao.CarDAO;
import dao.UserDAO;
import io.javalin.http.Handler;
import model.Booking;
import model.Car;
import model.User;

import java.util.ArrayList;

public class BookingController {
    public static Handler getBookingsByCar = ctx ->{
        int car_id;
        String car_id_str;
        car_id_str = ctx.queryParam("car_id");
        if (car_id_str==null){
            ctx.json(new Status("You are missing `car_id`"));
            return;
        }
        car_id = Integer.parseInt(car_id_str);

        ArrayList<Booking> bookings = BookingDAO.getBookingsByCar(car_id);
        if (bookings!=null){
            ctx.json(new Status(bookings));
            return;
        }
        ctx.json(new Status("No bookings with those details can be found"));
        return;
    };
    public static Handler getBookingsByUser = ctx ->{
        int user_id;
        String user_id_str;
        user_id_str = ctx.queryParam("user_id");
        if (user_id_str==null){
            ctx.json(new Status("You are missing `user_id`"));
            return;
        }
        user_id = Integer.parseInt(user_id_str);
        ArrayList<Booking> bookings = BookingDAO.getBookingsByUser(user_id);
        if (bookings!=null){
            ctx.json(new Status(bookings));
            return;
        }
        ctx.json(new Status("No bookings with those details can be found"));
        return;
    };
    public static Handler createBooking = ctx ->{
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
        if (user==null){
            ctx.json(new Status("Invalid credentials"));
            return;
        }

        String str_car_id = ctx.formParam("car_id");
        if (str_car_id==null) {
            ctx.json(new Status("No 'car_id' Provided"));
            return;
        }
        int car_id = Integer.parseInt(str_car_id);
        Booking booking = user.createBooking(car_id);
        ctx.json(new Status(booking));


    };
    public static Handler removeBooking = ctx ->{

    };
}
