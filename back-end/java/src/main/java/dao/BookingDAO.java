package dao;

import dao.util.DatabaseUtils;
import model.Booking;
import model.Car;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BookingDAO {
    public static ArrayList<Booking> getBookingsByCar(int car_id) {
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        try {
            // Here you prepare your sql statement
            String sql = "SELECT * FROM `rentalux`.bookings where `car_id` = " + car_id;
            // Execute the query
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // If you have multiple results, you do a while
            while(result.next()) {
                // 2) Add it to the list we have prepared
                bookings.add(new Booking(result.getInt("booking_id"), result.getInt("user_id"), result.getInt("car_id"),
                        result.getInt("start_bay_id"), result.getInt("end_bay_id"),result.getInt("duration"),
                        result.getDouble("rate"), result.getTimestamp("date")));
            }

            // Close it
            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if(!bookings.isEmpty()) {
            return bookings;
        }
        return null;
    }
    public static ArrayList<Booking> getBookingsByUser(int user_id) {
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        try {
            // Here you prepare your sql statement
            String sql = "SELECT * FROM `rentalux`.bookings where `user_id` = " + user_id;
            // Execute the query
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // If you have multiple results, you do a while
            while(result.next()) {
                // 2) Add it to the list we have prepared
                bookings.add(new Booking(result.getInt("booking_id"), result.getInt("user_id"), result.getInt("car_id"),
                        result.getInt("start_bay_id"), result.getInt("end_bay_id"),result.getInt("duration"),
                        result.getDouble("rate"), result.getTimestamp("date")));
            }

            // Close it
            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if(!bookings.isEmpty()) {
            return bookings;
        }
        return null;
    }
}
