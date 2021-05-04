package dao;

import dao.util.DatabaseUtils;
import model.Bay;
import model.Car;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
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
            System.out.println(sql);

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

    public static Car createCar(double cost, String colour, String lp, String make, int year, int bayid) {
        return null;
    }

    public static boolean removeCar(int car_id) {
        return false;
    }
}
