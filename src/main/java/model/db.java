package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class db{
    Dotenv dotenv = Dotenv.load();
    String db_password = dotenv.get("db_password");
    String db_user = dotenv.get("db_user");
    String url = "jdbc:mysql://localhost:3306/library_db?user=" + db_user + "&password=" + db_password;
    Connection connection = null;
    public db() {
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to database established successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
    }
}
