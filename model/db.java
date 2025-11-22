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

    public String viewAllBooks() {
        if (connection == null) {
            System.err.println("Cannot view books because database connection is not available.");
            return "Error: No database connection.";
        }

        StringBuilder books = new StringBuilder();
        String sql = "SELECT * FROM Library";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                books.append("ID: ").append(rs.getInt("id"))
                        .append(", Title: ").append(rs.getString("title"))
                        .append(", Author: ").append(rs.getString("author"))
                        .append(", ISBN: ").append(rs.getString("isbn"))
                        .append(", Available: ").append(rs.getInt("available"))
                        .append("\n");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching books: " + e.getMessage());
            return "Error: " + e.getMessage();
        }

        return books.toString();
    }

    public boolean checkoutBook(String name){
        if (connection == null) {
            System.err.println("Cannot checkout book because database connection is not available.");
            return false;
        }

        String sql = "UPDATE Library SET availible = availible - 1 WHERE title = ? AND availible > 0";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error checking out book: " + e.getMessage());
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