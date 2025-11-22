package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Statement;

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

    public Connection getConnection() {
        return connection;
    }

    public boolean addBook(String title, String author, String isbn, boolean available) {
        String query = "INSERT INTO books (title, author, isbn, available) VALUES (" + title + ", " + author + ", " + isbn + ", " + available + ")";
        try (Statement stmt = connection.createStatement()) {
            int rowsAffected = stmt.executeUpdate(query);
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error adding book: " + e.getMessage());
            return false;  
        
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Failed to close the database connection: " + e.getMessage());
            }
        }
    }

}