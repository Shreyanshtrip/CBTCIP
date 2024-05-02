//DIGITAL LIBRARY MANAGEMENT SYSTEM
/*
The main objective of this project is to provide a complete automated Library by digitizing each and every functionality. Starting from the book-keeping, issuing of books, fine generation, advance booking and #report generation all will be accomplished under one single project. The project will be a web based project with a database server responsible for maintaining every single detall of the Library. It has a very #user friendly interface which can easily be operated by any non-technical person.

#There are essentially two modules of this software;

#• Admin module: Admin will have complete control over the system. Admin has permissions

#to update, delete or modify any existing record or make a new entry (books and members).

#• Users: The normal users enjoy only limited privileges. They have a view access to the books. They can browse through the categories, search for a particular book, return and issue a book. They are also provided #with an email option in case of a query.*/
package Libaray;
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean available;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class User {
    private String userId;
    private String name;
    private List<Book> issuedBooks;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.issuedBooks = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public List<Book> getIssuedBooks() {
        return issuedBooks;
    }

    public void issueBook(Book book) {
        issuedBooks.add(book);
    }

    public void returnBook(Book book) {
        issuedBooks.remove(book);
    }
}

class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void displayBooks() {
        System.out.println("Books in the library:");
        for (Book book : books) {
            System.out.println("Book ID: " + book.getBookId() +
                    ", Title: " + book.getTitle() +
                    ", Author: " + book.getAuthor() +
                    ", Available: " + (book.isAvailable() ? "Yes" : "No"));
        }
    }

    public Book findBookById(String bookId) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    public User findUserById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
}

class Admin {
    public static void manageLibrary(Library library) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter Book ID:");
                    String bookId = scanner.nextLine();
                    System.out.println("Enter Title:");
                    String title = scanner.nextLine();
                    System.out.println("Enter Author:");
                    String author = scanner.nextLine();

                    Book newBook = new Book(bookId, title, author);
                    library.addBook(newBook);
                    System.out.println("Book added successfully.");
                    break;
                case 2:
                    library.displayBooks();
                    break;
                case 3 :
                    System.out.println("Exiting Admin Menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

class UserMenu {
    public static void userMenu(User user, Library library) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("User Menu:");
            System.out.println("1. Display Books");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. View Issued Books");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Display Books
                    library.displayBooks();
                    break;
                case 2:
                    // Issue Book
                    System.out.println("Enter the Book ID to issue:");
                    String issueBookId = scanner.nextLine();
                    Book issueBook = library.findBookById(issueBookId);
                    if (issueBook != null && issueBook.isAvailable()) {
                        user.issueBook(issueBook);
                        issueBook.setAvailable(false);
                        System.out.println("Book issued successfully.");
                    } else {
                        System.out.println("Book is not available for issue.");
                    }
                    break;
                case 3:
                    // Return Book
                    System.out.println("Enter the Book ID to return:");
                    String returnBookId = scanner.nextLine();
                    Book returnBook = library.findBookById(returnBookId);
                    if (returnBook != null && !returnBook.isAvailable()) {
                        user.returnBook(returnBook);
                        returnBook.setAvailable(true);
                        System.out.println("Book returned successfully.");
                    } else {
                        System.out.println("Invalid book or book not issued by this user.");
                    }
                    break;
                case 4:
                    // View Issued Books
                    List<Book> issuedBooks = user.getIssuedBooks();
                    System.out.println("Books issued by " + user.getName() + ":");
                    for (Book issuedBook : issuedBooks) {
                        System.out.println("Book ID: " + issuedBook.getBookId() +
                                ", Title: " + issuedBook.getTitle() +
                                ", Author: " + issuedBook.getAuthor());
                    }
                    break;
                case 5:
                    // Exit
                    System.out.println("Exiting User Menu.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

public class DigitalLibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Digital Library Management System!");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Admin Login
                    Admin.manageLibrary(library);
                    break;
                case 2:
                    // User Login
                    System.out.println("Enter User ID:");
                    String userId = scanner.nextLine();
                    User user = library.findUserById(userId);

                    if (user != null) {
                        UserMenu.userMenu(user, library);
                    } else {
                        System.out.println("User not found. Please try again.");
                    }
                    break;
                case 3:
                    // Exit
                    System.out.println("Exiting the Digital Library Management System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
