package controller;
import views.CliView;
import controller.MenuOption;


public class Controller {
    private CliView view;

    public Controller() {
        view = new CliView();
        // Constructor logic if needed
    }

    public void start() {
        view.displayWelcomeMessage();
        getInput();
    }

    public void getInput(){
        while (true) {
            MenuOption choice = view.getUserMenuChoice();
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
                    // Example data, in real scenario, get from user
                    String title = "Sample Book";
                    String author = "Author Name";
                    addBook(title, author);
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
    
    public void addBook(String title, String author) {
        //adding db logic later
        System.out.println("Adding book: " + title + " by " + author);
    }

    public void viewAllBooks() {
        //adding db logic later
        System.out.println("Viewing all books in the library.");
    }

    public void deleteBook(int bookId) {
        //adding db logic later
        System.out.println("Deleting book with ID: " + bookId);
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