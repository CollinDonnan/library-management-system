package controller;

import model.db;
import views.CliView;

public class Controller {
    private final CliView view;
    private final db database;

    public Controller() {
        view = new CliView();
        database = new db();
    }

    public void start() {
        view.displayWelcomeMessage();
        getInput();
    }

    public void getInput(){
        while (true) {
            MenuOption choice = view.getUserMenuChoice();
            if (choice == null) {
                view.displayErrorMessage("Please choose a valid option.");
                continue;
            }
            switch (choice) {
                case VIEW_ALL_BOOKS:
                    viewAllBooks();
                    break;
                case VIEW_BOOK_DETAILS:
                    // Example bookId, in real scenario, get from user
                    int bookId = 1;
                    String details = getBookDetails(bookId);
                    view.displayBookDetails(details);
                    break;
                case ADD_NEW_BOOK:
                    String title = view.promptForString("Enter book title: ");
                    String author = view.promptForString("Enter author name: ");
                    String isbn = view.promptForString("Enter ISBN: ");
                    int available = view.promptForInt("Enter quantity available: ");
                    addBook(title, author, isbn, available);
                    break;
                case UPDATE_BOOK:
                    // Example data, in real scenario, get from user
                    int updateBookId = 1;
                    String newTitle = "Updated Title";
                    String newAuthor = "Updated Author";
                    updateBook(updateBookId, newTitle, newAuthor);
                    break;
                case DELETE_BOOK:
                    // Example bookId, in real scenario, get from user
                    int deleteBookId = 1;
                    deleteBook(deleteBookId);
                    break;
                case EXIT:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
    }
        }
    }
    
    public void addBook(String title, String author, String isbn, int available) {
        boolean success = database.addBook(title, author, isbn, available);
        if (success) {
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Failed to add book.");
        }
    }

    public void viewAllBooks() {
        //adding db logic later
        System.out.println("Viewing all books in the library.");
    }

    public void deleteBook(String name) {
        boolean success = database.deleteBook(name);
                if (success) {
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Failed to add book.");
        }
    }

    public void updateBook(int bookId, String newTitle, String newAuthor) {
        //adding db logic later
        System.out.println("Updating book with ID: " + bookId + " to " + newTitle + " by " + newAuthor);
    }

    public String getBookDetails(int bookId) {
        //adding db logic later
        return "Details of book with ID: " + bookId;
    }

    public void checkoutBook(int bookId, int userId) {
        //adding db logic later
        System.out.println("User with ID: " + userId + " checked out book with ID: " + bookId);
    }
}