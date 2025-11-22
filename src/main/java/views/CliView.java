package views;
import controller.MenuOption;
import java.util.Scanner;

public class CliView {
    private Scanner input;
    public CliView() {
        input = new Scanner(System.in);
    }
    public void displayWelcomeMessage() {
        System.out.println("Welcome to the Library Management System!");
    }
    public MenuOption getUserMenuChoice() {
        System.out.println("Please choose an option:");
        System.out.println("1. View All Books");
        System.out.println("2. View Book Details");
        System.out.println("3. Add New Book");
        System.out.println("4. Update Book");
        System.out.println("5. Delete Book");
        System.out.println("6. Exit");
        String choice = input.nextLine();
        switch (choice) {
            case "1": return MenuOption.VIEW_ALL_BOOKS;
            case "2": return MenuOption.VIEW_BOOK_DETAILS;
            case "3": return MenuOption.ADD_NEW_BOOK;
            case "4": return MenuOption.UPDATE_BOOK;
            case "5": return MenuOption.DELETE_BOOK;
            case "6": return MenuOption.EXIT;
            default: return null;
        }
    }
    public void displayBookDetails(String bookDetails) {
        System.out.println("Book Details: " + bookDetails);
    }
    public void displayErrorMessage(String errorMessage) {
        System.err.println("Error: " + errorMessage);
    }
}
