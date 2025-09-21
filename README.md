# Library Management System

A simple Java-based library management system for managing books, patrons, and lending operations.

## Features

### Book Management
- Add, update, and search books by ISBN, title, or author
- Track book availability and borrowing status

### Patron Management  
- Add new library members
- Update patron information (name, email, phone)
- Track patron borrowing history

### Lending Process
- Book checkout and return functionality
- Automatic availability status updates
- Borrowing history tracking

### Inventory Management
- View available and borrowed books
- Get real-time inventory counts
- Filter books by availability status

## Quick Start

1. Clone the repository
2. Run `LibraryDemo.java` to see all features in action

## Core Classes

- `Book` - Represents library books with ISBN, title, author, and availability
- `Patron` - Represents library members with contact info and borrowing history  
- `LibraryService` - Main service class handling all operations

## Usage Example

```java
LibraryService library = new LibraryService();

// Add books and patrons
library.addBook(new Book("123", "Java Basics", "Author", 2023));
library.addPatron(new Patron("P001", "John Doe", "john@email.com", "123-456-7890"));

// Checkout and return
library.borrowBook("P001", "123");
library.returnBook("123");

// Check inventory
System.out.println("Available: " + library.getAvailableCount());
```
