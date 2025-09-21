package com.example.libraryManagement;

import com.example.libraryManagement.model.Book;
import com.example.libraryManagement.model.Patron;
import com.example.libraryManagement.service.LibraryService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class LibraryManagementApplication {

	public static void main(String[] args) {
        LibraryService library = new LibraryService();

        // Add books
        library.addBook(new Book("978-0134685991", "Java basics", "Joshua Bloch", 2017));
        library.addBook(new Book("978-0596009205", "Head First Design Patterns", "Eric Freeman", 2004));
        library.addBook(new Book("978-0321356680", "Java basics", "Joshua Bloch", 2008));

        // Search by title
        System.out.println("Books with 'Java' in title:");
        List<Book> javaBooks = library.searchByTitle("Java");
        javaBooks.forEach(System.out::println);

        // Search by author
        System.out.println("Books by Joshua Bloch:");
        List<Book> blochBooks = library.searchByAuthor("Joshua Bloch");
        blochBooks.forEach(System.out::println);

        // Search by ISBN
        System.out.println("Book with ISBN 978-0134685991:");
        Book book = library.searchByIsbn("978-0134685991");
        System.out.println(book);

        // Update book
        Book updatedBook = new Book("978-0134685991", "Java basics 3rd Edition", "Joshua Bloch", 2017);
        library.updateBook("978-0134685991", updatedBook);
        System.out.println("After update:");
        System.out.println(library.searchByIsbn("978-0134685991"));

        // Patron Management Demo
        System.out.println("\n=== Patron Management ===");

        // Add patrons
        Patron patron1 = new Patron("P001", "John Doe", "john@email.com", "123-456-7890");
        Patron patron2 = new Patron("P002", "Jane Smith", "jane@email.com", "098-765-4321");

        library.addPatron(patron1);
        library.addPatron(patron2);
        System.out.println("Added patrons: " + patron1.getName() + ", " + patron2.getName());

        // Update patron info
        library.updatePatron("P001", "John Doe Jr.", "john.jr@email.com", "123-456-7890");
        System.out.println("Updated patron: " + library.getPatron("P001"));

        // === Lending Process Demo ===
        System.out.println("\n=== Book Checkout Process ===");

        // Checkout books
        boolean checkout1 = library.borrowBook("P001", "978-0134685991");
        System.out.println("Checkout 'Java basics 3rd Edition' to John: " + (checkout1 ? "SUCCESS" : "FAILED"));

        boolean checkout2 = library.borrowBook("P001", "978-0596009205");
        System.out.println("Checkout 'Head First Design Patterns' to John: " + (checkout2 ? "SUCCESS" : "FAILED"));

        boolean checkout3 = library.borrowBook("P002", "978-0321356680");
        System.out.println("Checkout 'Java basics' to Jane: " + (checkout3 ? "SUCCESS" : "FAILED"));

        // Try to checkout already borrowed book
        boolean checkout4 = library.borrowBook("P002", "978-0134685991");
        System.out.println("Try checkout already borrowed book to Jane: " + (checkout4 ? "SUCCESS" : "FAILED"));

        System.out.println("\n=== Current Book Status ===");
        System.out.println(library.searchByIsbn("978-0134685991"));
        System.out.println(library.searchByIsbn("978-0596009205"));
        System.out.println(library.searchByIsbn("978-0321356680"));

        System.out.println("\n=== Borrowing History ===");
        System.out.println("P001 History: " + library.getPatronBorrowingHistory("P001"));
        System.out.println("P002 History: " + library.getPatronBorrowingHistory("P002"));

        System.out.println("\n=== Book Return Process ===");
        boolean return1 = library.returnBook("978-0134685991");
        System.out.println("Return 'Java basics 3rd Edition': " + (return1 ? "SUCCESS" : "FAILED"));

        // Try to return already returned book
        boolean return2 = library.returnBook("978-0134685991");
        System.out.println("Try return already returned book: " + (return2 ? "SUCCESS" : "FAILED"));

        System.out.println("\n=== Final Book Status ===");
        System.out.println(library.searchByIsbn("978-0134685991"));

        // === Inventory Management Demo ===
        System.out.println("\n=== Inventory Management ===");
        System.out.println("Total Books: " + library.getTotalBooks());
        System.out.println("Available Books: " + library.getAvailableCount());
        System.out.println("Borrowed Books: " + library.getBorrowedCount());

        System.out.println("\n=== Available Books List ===");
        library.getAvailableBooks().forEach(System.out::println);

        System.out.println("\n=== Borrowed Books List ===");
        library.getBorrowedBooks().forEach(System.out::println);
    }
}
