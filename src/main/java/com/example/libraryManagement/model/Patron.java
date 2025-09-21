package com.example.libraryManagement.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Patron {
    private String patronId;
    private String name;
    private String email;
    private String phone;
    private List<String> borrowingHistory;

    public Patron(String patronId, String name, String email, String phone) {
        this.patronId = patronId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.borrowingHistory = new ArrayList<>();
    }

    // Getters and Setters
    public String getPatronId() { return patronId; }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public String getPhone() { return phone; }

    public List<String> getBorrowingHistory() { return new ArrayList<>(borrowingHistory); }

    public void setName(String name) { this.name = name; }

    public void setEmail(String email) { this.email = email; }

    public void setPhone(String phone) { this.phone = phone; }

    public void addToBorrowingHistory(String isbn) {
        borrowingHistory.add(isbn);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patron patron = (Patron) o;
        return Objects.equals(patronId, patron.patronId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patronId);
    }

    @Override
    public String toString() {
        return String.format("Patron{id='%s', name='%s', email='%s'}",
                patronId, name, email);
    }
}
