package com.example.libraryManagement.service;

import com.example.libraryManagement.model.Book;
import com.example.libraryManagement.model.Patron;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LibraryService {

    private Map<String, Book> books = new HashMap<>();
    private Map<String, Patron> patrons = new HashMap<>();

    public boolean addBook(Book book) {
        if (books.containsKey(book.getIsbn())) {
            return false;
        }
        books.put(book.getIsbn(), book);
        return true;
    }

    public boolean removeBook(String isbn) {
        return books.remove(isbn) != null;
    }

    public boolean updateBook(String isbn, Book updatedBook) {
        if (!books.containsKey(isbn)) {
            return false;
        }
        books.put(isbn, updatedBook);
        return true;
    }

    public List<Book> searchByTitle(String title) {
        return books.values().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> searchByAuthor(String author) {
        return books.values().stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Book searchByIsbn(String isbn) {
        return books.get(isbn);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }

    // Patron Management
    public boolean addPatron(Patron patron) {
        if (patrons.containsKey(patron.getPatronId())) {
            return false;
        }
        patrons.put(patron.getPatronId(), patron);
        return true;
    }


    public boolean updatePatron(String patronId, String name, String email, String phone) {
        Patron patron = patrons.get(patronId);
        if (patron == null) return false;
        patron.setName(name);
        patron.setEmail(email);
        patron.setPhone(phone);
        return true;
    }

    public Patron getPatron(String patronId) {
        return patrons.get(patronId);
    }

    public List<String> getPatronBorrowingHistory(String patronId) {
        Patron patron = patrons.get(patronId);
        return patron != null ? patron.getBorrowingHistory() : new ArrayList<>();
    }

    public boolean borrowBook(String patronId, String isbn) {
        Patron patron = patrons.get(patronId);
        Book book = books.get(isbn);
        if (patron == null || book == null || !book.isAvailable()) {
            return false;
        }
        book.borrowBook(patronId);
        patron.addToBorrowingHistory(isbn);
        return true;
    }

    public boolean returnBook(String isbn) {
        Book book = books.get(isbn);
        if (book == null || book.isAvailable()) {
            return false;
        }
        book.returnBook();
        return true;
    }

    // Inventory Management
    public List<Book> getAvailableBooks() {
        return books.values().stream()
                .filter(Book::isAvailable)
                .collect(Collectors.toList());
    }

    public List<Book> getBorrowedBooks() {
        return books.values().stream()
                .filter(book -> !book.isAvailable())
                .collect(Collectors.toList());
    }

    public int getTotalBooks() {
        return books.size();
    }

    public int getAvailableCount() {
        return (int) books.values().stream().filter(Book::isAvailable).count();
    }

    public int getBorrowedCount() {
        return (int) books.values().stream().filter(book -> !book.isAvailable()).count();
    }
}
