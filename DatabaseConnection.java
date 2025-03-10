package SwingJDBC;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        try (InputStream input = DatabaseConnection.class.getResourceAsStream("/SwingJDBC/config.properties")) {
            if (input == null) {
                System.out.println("⚠ Could not find config.properties! Check the file path.");
            } else {
                Properties properties = new Properties();
                properties.load(input);

                URL = properties.getProperty("DB_URL");
                USER = properties.getProperty("DB_USER");
                PASSWORD = properties.getProperty("DB_PASSWORD");

                System.out.println("✅ Database config loaded: " + URL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("⚠ Error loading database configuration!");
        }
    }

    public static Connection getConnection() {
        if (URL == null || USER == null || PASSWORD == null) {
            System.out.println("❌ Database credentials are not loaded properly!");
            return null;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL driver
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            conn.setAutoCommit(true); // Ensure changes are saved automatically
            return conn;
        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL JDBC Driver not found!");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.out.println("❌ Database connection failed!");
            e.printStackTrace();
            return null;
        }
    }

}
