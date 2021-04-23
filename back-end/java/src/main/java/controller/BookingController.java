package controller;

import controller.util.Status;
import dao.BookingDAO;
import dao.CarDAO;
import io.javalin.http.Handler;
import model.Booking;
import model.Car;

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

    };
    public static Handler removeBooking = ctx ->{

    };
}
