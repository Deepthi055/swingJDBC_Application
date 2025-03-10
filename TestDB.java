package SwingJDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDB {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM cart")) {

            System.out.println("🍔 Cart Items:");
            while (rs.next()) {
                System.out.println("🔹 ID: " + rs.getInt("id") + 
                                   ", Name: " + rs.getString("item_name") + 
                                   ", Price: ₹" + rs.getDouble("price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


