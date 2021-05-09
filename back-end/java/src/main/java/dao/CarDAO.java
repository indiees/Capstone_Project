package dao;

import dao.util.DatabaseUtils;
import model.Bay;
import model.Booking;
import model.Car;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CarDAO {
    public static ArrayList<Car> getCars() {
        ArrayList<Car> cars = new ArrayList<Car>();
        try {
            // Here you prepare your sql statement
            String sql = "SELECT * FROM `rentalux`.cars";
            // Execute the query
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // If you have multiple results, you do a while
            while(result.next()) {
                // 2) Add it to the list we have prepared
                cars.add(new Car(result.getInt("car_id"), result.getDouble("cost"),
                        result.getString("color"), result.getString("liscence_plate"),
                        result.getString("make"), result.getInt("year"), result.getInt("bay_id")));
            }

            // Close it
            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if(!cars.isEmpty()) {
            return cars;
        }
        return null;
    }

    public static Car getCar(int car_id) {
        ArrayList<Car> cars = new ArrayList<Car>();
        try {
            // Here you prepare your sql statement
            String sql = "SELECT * FROM `rentalux`.cars where `car_id` = " + car_id;
            // Execute the query
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // If you have multiple results, you do a while
            while(result.next()) {
                // 2) Add it to the list we have prepared
                cars.add(new Car(result.getInt("car_id"), result.getDouble("cost"),
                        result.getString("color"), result.getString("liscence_plate"),
                        result.getString("make"), result.getInt("year"), result.getInt("bay_id")));
            }

            // Close it
            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if(!cars.isEmpty()) {
            return cars.get(0);
        }
        return null;
    }

    public static ArrayList<Car> getCars(HashMap<String,String> props) {
        ArrayList<Car> cars = new ArrayList<Car>();
        try {
            // Here you prepare your sql statement
            String sql = "SELECT * FROM `rentalux`.cars";
            if (props.size()>0){
                sql += " where ";
                for (Map.Entry<String,String> item : props.entrySet()){
                    String key = item.getKey();
                    String value = item.getValue();
                    if (key.contains("_min")){
                        sql += "`" + key.replace("_min","") + "` > " + value + " and ";
                    }
                    else if (key.contains("_max")){
                        sql += "`" + key.replace("_max","") + "` < " + value + " and ";
                    }
                    else{
                        sql += "`" + key + "`" + " like '" + value + "' and ";
                    }
                }
                sql = sql.replaceAll(" and $",";");
            }

            // Execute the query
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // If you have multiple results, you do a while
            while(result.next()) {
                // 2) Add it to the list we have prepared
                cars.add(new Car(result.getInt("car_id"), result.getDouble("cost"),
                        result.getString("color"), result.getString("liscence_plate"),
                        result.getString("make"), result.getInt("year"), result.getInt("bay_id")));
            }

            // Close it
            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if(!cars.isEmpty()) {
            return cars;
        }
        return null;
    }

    public static ArrayList<String> getLocations() {
        ArrayList<String> locations = new ArrayList<String>();
        try {
            // Here you prepare your sql statement
            String sql = "select distinct location from rentalux.cars join rentalux.bays on cars.bay_id = bays.bay_id;";
            // Execute the query
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // If you have multiple results, you do a while
            while(result.next()) {
                // 2) Add it to the list we have prepared
                locations.add(result.getString("location"));
            }

            // Close it
            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if(!locations.isEmpty()) {
            return locations;
        }
        return null;
    }

    public static ArrayList<String> getColors() {
        ArrayList<String> colors = new ArrayList<String>();
        try {
            // Here you prepare your sql statement
            String sql = "select distinct color from rentalux.cars;";
            // Execute the query
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // If you have multiple results, you do a while
            while(result.next()) {
                // 2) Add it to the list we have prepared
                colors.add(result.getString("color"));
            }

            // Close it
            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if(!colors.isEmpty()) {
            return colors;
        }
        return null;
    }
    public static ArrayList<String> getMakes() {
        ArrayList<String> make = new ArrayList<String>();
        try {
            // Here you prepare your sql statement
            String sql = "select distinct make from rentalux.cars;";
            // Execute the query
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // If you have multiple results, you do a while
            while(result.next()) {
                // 2) Add it to the list we have prepared
                make.add(result.getString("make"));
            }

            // Close it
            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if(!make.isEmpty()) {
            return make;
        }
        return null;
    }

    public static Car createCar(double cost, String color, String liscence_plate, String make, int year, int bay_id) {
        int car_id=0;
        String sql = "INSERT INTO `rentalux`.`cars`(`cost`,`color`,`liscence_plate`,`make`,`year`, `bay_id`) VALUES(" + cost + ",'" + color
                + "','" + liscence_plate + "','" + make + "','" + year + "'," + bay_id + ");";

        try {
            // Execute the query
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            // Extract user_id
            ResultSet result = statement.getGeneratedKeys();
            result.next();
            car_id = result.getInt(1);
            // Close it
            DatabaseUtils.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        // Create the user object
        Car new_car = new Car(car_id, cost, color, liscence_plate, make, year, bay_id);
        return new_car;
    }

    public static boolean updateCar(int car_id, HashMap<String, String> props) {
        if (props.size()>0) {
            String sql;
            sql = "UPDATE `rentalux`.`cars` SET ";
            Iterator propIterator = props.entrySet().iterator();
            while (propIterator.hasNext()) {
                Map.Entry prop = (Map.Entry) propIterator.next();
                sql += "`" + prop.getKey() + "` = '" + prop.getValue() + "',";
            }
            sql = sql.substring(0, sql.length() - 1);
            sql += " WHERE `car_id` = " + car_id + ";";
            System.out.println(sql);
            try {
                // Execute the query
                Connection connection = DatabaseUtils.connectToDatabase();
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
                // Close it
                DatabaseUtils.closeConnection(connection);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        else{
            System.out.println("UpdateCar DAO method was called, but there were no props provided");
        }
        return true;
    }

    public static boolean removeCar(int car_id) {
        String delete_sql;
        delete_sql= "DELETE FROM `rentalux`.`cars` WHERE `car_id` = '" + car_id + "';";

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
