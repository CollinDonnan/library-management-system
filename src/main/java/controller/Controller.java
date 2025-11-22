package controller;
import views.CliView;
import controller.MenuOption;

public class Controller {
    private CliView view;
    public Controller() {
        view = new CliView();
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
                    int bookId = 1;
                    String details = getBookDetails(bookId);
                    view.displayBookDetails(details);
                    break;
                case ADD_NEW_BOOK:
                    String title = "Sample Book";
                    String author = "Author Name";
                    addBook(title, author);
                    break;
                case UPDATE_BOOK:
                    int updateBookId = 1;
                    String newTitle = "Updated Title";
                    String newAuthor = "Updated Author";
                    updateBook(updateBookId, newTitle, newAuthor);
                    break;
                case DELETE_BOOK:
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
        System.out.println("Adding book: " + title + " by " + author);
    }
    public void viewAllBooks() {
        System.out.println("Viewing all books...");
    }
    public String getBookDetails(int bookId) {
        return "Book details for book ID: " + bookId;
    }
    public void updateBook(int bookId, String newTitle, String newAuthor) {
        System.out.println("Updating book ID " + bookId + " to title: " + newTitle + ", author: " + newAuthor);
    }
    public void deleteBook(int bookId) {
        System.out.println("Deleting book ID: " + bookId);
    }
}
