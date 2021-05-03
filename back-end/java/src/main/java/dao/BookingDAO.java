package dao;

import controller.util.Utils;
import dao.util.DatabaseUtils;
import model.Booking;
import model.Car;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
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

    public static Booking createBooking(int car_id, int user_id, int start_bay_id, int end_bay_id, Timestamp date, int duration, double rate) {
        String update_sql;
        int booking_id = 0;
        update_sql = "INSERT INTO `rentalux`.`bookings` ( `car_id`, `user_id`, `start_bay_id`,`date`,`rate`) " +
                "VALUES('" + car_id + "' ,'" + user_id + "' ,'" + start_bay_id +
                "' ,'" + date + "' ,'" + rate + "');";

        try {
            // Execute the query
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            statement.executeUpdate(update_sql, Statement.RETURN_GENERATED_KEYS);
            // Extract user_id
            ResultSet result = statement.getGeneratedKeys();
            result.next();
            booking_id = result.getInt(1);
            // Close it
            DatabaseUtils.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        // Create the user object
        Booking booking = new Booking(booking_id, car_id, user_id, start_bay_id, end_bay_id, duration, rate, date);
        return booking;
    }

    public static Boolean removeBooking(int booking_id, int user_id) {
        System.out.println("trying to remove " + booking_id);
        String delete_sql;
        delete_sql= "DELETE FROM `rentalux`.`bookings` WHERE `booking_id` = '" + booking_id + "' and `user_id` = " + user_id + ";";

        try {
            // Execute the sql
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            statement.execute(delete_sql);
            // Close it
            DatabaseUtils.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

