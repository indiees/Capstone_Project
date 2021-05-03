package dao;

import dao.util.DatabaseUtils;
import model.Bay;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BayDAO {

    public static ArrayList<Bay> getBays() {
        ArrayList<Bay> bays = new ArrayList<Bay>();
        try {
            // Here you prepare your sql statement
            String sql = "SELECT * FROM `rentalux`.bays";
            // Execute the query
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // If you have multiple results, you do a while
            while(result.next()) {
                // 2) Add it to the list we have prepared
                System.out.println(result.getInt("bay_id"));
                bays.add(new Bay(result.getInt("bay_id"), result.getString("location"), result.getInt("max_capacity")));
            }

            // Close it
            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(bays.size());
        if(!bays.isEmpty()) {
            return bays;
        }
        return null;
    }

    public static Bay getBay(int bay_id) {
        return null;
    }

    public static Bay createBay(String location, int maxCap) {
        return null;
    }

    public static boolean removeBay(int bay_id) {
        return false;
    }
}
