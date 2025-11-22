package model;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class db {

    private final Connection connection;

    public db() {
        Connection tempConnection = null;
        try {
            Dotenv dotenv = Dotenv.load();
            String user = dotenv.get("db_user");
            String password = dotenv.get("db_password");
            if (user == null || password == null) {
                throw new SQLException("Missing database credentials in .env file.");
            }


            String url = "jdbc:mysql://localhost:3306/library_db";
            tempConnection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database.");
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
        connection = tempConnection;
    }

    public boolean addBook(String title, String author, String isbn, int available) {
        if (connection == null) {
            System.err.println("Cannot add book because database connection is not available.");
            return false;
        }

        String sql = "INSERT INTO Library (title, author, isbn, availible) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, isbn);
            stmt.setInt(4, available);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding book: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteBook(int bookId) {
        if (connection == null) {
            System.err.println("Cannot delete book because database connection is not available.");
            return false;
        }

        String sql = "DELETE FROM Library WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting book: " + e.getMessage());
            return false;
        }
    }



    public void close() {
        if (connection == null) {
            return;
        }

        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Error closing database connection: " + e.getMessage());
        }
    }
}