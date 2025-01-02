/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aqilm
 */
import javax.swing.*;

import javax.swing.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        BookManager manager = new BookManager();

        while (true) {
            String menu = "📚 Welcome to the Book Management System! 📚\n"
                        + "----------------------------------------\n"
                        + "1️⃣ Add a Book\n"
                        + "2️⃣ Remove a Book\n"
                        + "3️⃣ Search for a Book\n"
                        + "4️⃣ View All Books\n"
                        + "5️⃣ Exit\n\n"
                        + "👉 Choose an option:";
            String choice = promptUser(menu);

            switch (choice) {
                case "1":
                    String title = promptUser("Enter Book Title:");
                    String author = promptUser("Enter Author:");
                    String isbn = promptUser("Enter ISBN:");
                    String genre = promptUser("Enter Genre:");

                    manager.addBook(new Book(title, author, isbn, genre));
                    JOptionPane.showMessageDialog(null, "🎉 Book added successfully!");
                    break;

                case "2":
                    String isbnToRemove = promptUser("Enter ISBN of the book to remove:");
                    if (manager.removeBook(isbnToRemove)) {
                        JOptionPane.showMessageDialog(null, "✅ Book removed successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "❌ Book not found.");
                    }
                    break;

                case "3":
                    String isbnToSearch = promptUser("Enter ISBN of the book to search:");
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
                    JOptionPane.showMessageDialog(null, "👋 Exiting the system. Have a great day!");
                    System.exit(0);

                default:
                    JOptionPane.showMessageDialog(null, "❗ Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Prompt the user for input and ensure it is not null or empty.
     * If the user clicks "Cancel," the program exits.
     */
    private static String promptUser(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(message);

            // Exit if the user clicks "Cancel"
            if (input == null) {
                JOptionPane.showMessageDialog(null, "👋 Exiting the system. Have a great day!");
                System.exit(0);
            }

            // Ensure input is not empty
            if (!input.trim().isEmpty()) {
                return input.trim();
            }

            // Show error message for empty input
            JOptionPane.showMessageDialog(null, "⚠️ Input cannot be empty. Please try again.");
        }
    }
}
