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
        HashSet<Integer> existingIsbns = new HashSet<>(); // To ensure ISBN uniqueness

        while (true) {
            String menu = "📚 Welcome to the Book Management System! 📚\n"
                        + "----------------------------------------\n"
                        + "1️⃣ Add a Book\n"
                        + "2️⃣ Remove a Book\n"
                        + "3️⃣ Search for a Book\n"
                        + "4️⃣ View All Books\n"
                        + "5️⃣ Exit\n\n"
                        + "👉 Choose an option:";
            String choice = promptForInput(menu);

            switch (choice) {
                case "1":
                    String title = promptForInput("Enter Book Title:");
                    String author = promptForInput("Enter Author:");
                    int isbn = promptForUniqueIsbn("Enter ISBN (numeric):", existingIsbns);
                    String genre = promptForInput("Enter Genre:");

                    manager.addBook(new Book(title, author, isbn, genre));
                    existingIsbns.add(isbn); // Add the ISBN to the set
                    JOptionPane.showMessageDialog(null, "🎉 Book added successfully!");
                    break;

                case "2":
                    int isbnToRemove = promptForInt("Enter ISBN of the book to remove:");
                    if (manager.removeBook(isbnToRemove)) {
                        existingIsbns.remove(isbnToRemove); // Remove from the set
                        JOptionPane.showMessageDialog(null, "✅ Book removed successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "❌ Book not found.");
                    }
                    break;

                case "3":
                    int isbnToSearch = promptForInt("Enter ISBN of the book to search:");
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
     * Prompt the user for non-empty input. Exits if canceled.
     *
     * @param message The message to display in the dialog box.
     * @return A non-empty string entered by the user.
     */
    private static String promptForInput(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(message);
            if (input == null) { // Handle cancel
                JOptionPane.showMessageDialog(null, "👋 Exiting the system. Have a great day!");
                System.exit(0);
            }
            if (!input.trim().isEmpty()) {
                return input.trim(); // Return valid input
            }
            JOptionPane.showMessageDialog(null, "⚠️ Input cannot be empty! Please try again.");
        }
    }

    /**
     * Prompt the user for a numeric input with validation. Exits if canceled.
     *
     * @param message The message to display in the dialog box.
     * @return A valid integer entered by the user.
     */
    private static int promptForInt(String message) {
        while (true) {
            try {
                String input = promptForInput(message);
                return Integer.parseInt(input); // Parse input as integer
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "⚠️ Invalid input! Please enter a valid number.");
            }
        }
    }

    /**
     * Prompt the user for a unique ISBN. Exits if canceled.
     *
     * @param message      The message to display in the dialog box.
     * @param existingIsbns The set of existing ISBNs.
     * @return A unique ISBN entered by the user.
     */
    private static int promptForUniqueIsbn(String message, HashSet<Integer> existingIsbns) {
        while (true) {
            int isbn = promptForInt(message);
            if (!existingIsbns.contains(isbn)) {
                return isbn; // Return unique ISBN
            }
            JOptionPane.showMessageDialog(null, "⚠️ ISBN already exists! Please enter a unique ISBN.");
        }
    }
}
