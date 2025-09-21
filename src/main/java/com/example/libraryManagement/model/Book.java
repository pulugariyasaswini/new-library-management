package com.example.libraryManagement.model;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private String isbn;

    private String title;

    private String author;

    private int publicationYear;

    private boolean available;

    private LocalDate borrowedDate;

    private String borrowedBy;

    public Book(String isbn, String title, String author, int publicationYear) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.available = true;
    }

    // Getters and Setters
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public boolean isAvailable() {
        return available;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void borrowBook(String patronId) {
        this.available = false;
        this.borrowedBy = patronId;
        this.borrowedDate = LocalDate.now();
    }

    public void returnBook() {
        this.available = true;
        this.borrowedBy = null;
        this.borrowedDate = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        return String.format("Book{isbn='%s', title='%s', author='%s', year=%d, available=%s}",
                isbn, title, author, publicationYear, available);
    }
}
