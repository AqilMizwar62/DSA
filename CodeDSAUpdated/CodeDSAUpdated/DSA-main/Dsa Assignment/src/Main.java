/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aqilm
 */

import javax.swing.*;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        BookManager manager = new BookManager();
        HashSet<Integer> existingIsbns = new HashSet<>();

        while (true) {
            String menu = "Welcome to the Book Management System! \n"
                        + "----------------------------------------\n"
                        + "1. Add a Book\n"
                        + "2. Remove a Book\n"
                        + "3. Search for a Book\n"
                        + "4. View All Books\n"
                        + "5. Borrow a Book\n"
                        + "6. Return a Book\n"
                        + "7. Exit\n\n"
                        + "Choose an option:";
            String choice = promptForInput(menu);

            if (choice == null) continue;

            switch (choice) {
                case "1":
                    String title = promptForInput("Enter Book Title:");
                    if (title == null) continue;

                    String author = promptForInput("Enter Author:");
                    if (author == null) continue;

                    Integer isbn = promptForUniqueIsbn("Enter ISBN (numeric):", existingIsbns);
                    if (isbn == null) continue;

                    String genre = promptForInput("Enter Genre:");
                    if (genre == null) continue;

                    manager.addBook(new Book(title, author, isbn, genre));
                    existingIsbns.add(isbn);
                    JOptionPane.showMessageDialog(null, "🎉 Book added successfully!");
                    break;

                case "2":
                    Integer isbnToRemove = promptForInt("Enter ISBN of the book to remove:");
                    if (isbnToRemove == null) continue;

                    if (manager.removeBook(isbnToRemove)) {
                        existingIsbns.remove(isbnToRemove);
                        JOptionPane.showMessageDialog(null, "✅ Book removed successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "❌ Book not found.");
                    }
                    break;

                case "3":
                    Integer isbnToSearch = promptForInt("Enter ISBN of the book to search:");
                    if (isbnToSearch == null) continue;

                    Book foundBook = manager.searchBook(isbnToSearch);
                    if (foundBook != null) {
                        JOptionPane.showMessageDialog(null, "🎯 Book found:\n" + foundBook);
                    } else {
                        JOptionPane.showMessageDialog(null, "❌ Book not found.");
                    }
                    break;

                case "4":
                    StringBuilder allBooks = new StringBuilder("📚 Books in the Library:\n\n");
                    manager.getAllBooks().forEach(book -> allBooks.append(book).append("\n\n"));
                    JOptionPane.showMessageDialog(null, allBooks.length() > 0 ? allBooks : "📭 No books available.");
                    break;

                case "5":
    String user = promptForInput("Enter your name:");
    if (user == null) continue;

    Integer isbnToBorrow = promptForInt("Enter ISBN of the book to borrow:");
    if (isbnToBorrow == null) continue;

    Book bookToBorrow = manager.searchBook(isbnToBorrow);
    if (bookToBorrow != null && !bookToBorrow.isBorrowed()) {
        manager.borrowBook(isbnToBorrow);
        manager.requestBorrow(user, isbnToBorrow);
        JOptionPane.showMessageDialog(null, "✅ Book borrowed successfully!");
    } else {
        manager.requestBorrow(user, isbnToBorrow);
        JOptionPane.showMessageDialog(null, "❌ Book unavailable. You are placed in the queue.");
    }
    break;

                case "6":
                    Integer isbnToReturn = promptForInt("Enter ISBN of the book to return:");
                    if (isbnToReturn == null) continue;

                    Book bookToReturn = manager.searchBook(isbnToReturn);
                    if (bookToReturn != null && bookToReturn.isBorrowed()) {
                        manager.returnBook(isbnToReturn);
                        JOptionPane.showMessageDialog(null, "✅ Book returned successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "❌ Book not found or not borrowed.");
                    }
                    break;

                case "7":
                    JOptionPane.showMessageDialog(null, "👋 Exiting the system. Have a great day!");
                    System.exit(0);

                default:
                    JOptionPane.showMessageDialog(null, "❗ Invalid choice. Please try again.");
            }
        }
    }

    private static String promptForInput(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(message);
            if (input == null) return null;
            if (!input.trim().isEmpty()) {
                return input.trim();
            }
            JOptionPane.showMessageDialog(null, "⚠️ Input cannot be empty! Please try again.");
        }
    }

    private static Integer promptForInt(String message) {
        while (true) {
            try {
                String input = promptForInput(message);
                if (input == null) return null;
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "⚠️ Invalid input! Please enter a valid number.");
            }
        }
    }

    private static Integer promptForUniqueIsbn(String message, HashSet<Integer> existingIsbns) {
        while (true) {
            Integer isbn = promptForInt(message);
            if (isbn == null) return null;
            if (!existingIsbns.contains(isbn)) {
                return isbn;
            }
            JOptionPane.showMessageDialog(null, "⚠️ ISBN already exists! Please enter a unique ISBN.");
        }
    }
}