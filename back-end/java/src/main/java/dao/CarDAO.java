package dao;

import dao.util.DatabaseUtils;
import model.Bay;
import model.Car;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
                        result.getString("make"), result.getInt("year")));
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
}
