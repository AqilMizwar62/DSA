/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aqilm
 */
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        BookManager manager = new BookManager();

        while (true) {
            String menu = "===== Book Management System =====\n"
                        + "1. Add a Book\n"
                        + "2. Remove a Book\n"
                        + "3. Search for a Book\n"
                        + "4. View All Books\n"
                        + "5. Exit\n";
            String choice = JOptionPane.showInputDialog(menu + "Choose an option:");

            if (choice == null) break; // Handle cancel

            switch (choice) {
                case "1":
                    String title = JOptionPane.showInputDialog("Enter Book Title:");
                    String author = JOptionPane.showInputDialog("Enter Author:");
                    String genre = JOptionPane.showInputDialog("Enter Genre:");
                    String isbn = JOptionPane.showInputDialog("Enter ISBN:");
                    manager.addBook(new Book(title, author, genre, isbn));
                    JOptionPane.showMessageDialog(null, "Book added successfully!");
                    break;

                case "2":
                    String isbnToRemove = JOptionPane.showInputDialog("Enter ISBN of the book to remove:");
                    if (manager.removeBook(isbnToRemove)) {
                        JOptionPane.showMessageDialog(null, "Book removed successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Book not found.");
                    }
                    break;

                case "3":
                    String isbnToSearch = JOptionPane.showInputDialog("Enter ISBN of the book to search:");
                    Book foundBook = manager.searchBook(isbnToSearch);
                    if (foundBook != null) {
                        JOptionPane.showMessageDialog(null, "Book found:\n" + foundBook);
                    } else {
                        JOptionPane.showMessageDialog(null, "Book not found.");
                    }
                    break;

                case "4":
                    StringBuilder allBooks = new StringBuilder("Books in the Library:\n");
                    manager.getAllBooks().forEach(book -> allBooks.append(book).append("\n"));
                    JOptionPane.showMessageDialog(null, allBooks.length() > 0 ? allBooks : "No books available.");
                    break;

                case "5":
                    JOptionPane.showMessageDialog(null, "Exiting the system. Goodbye!");
                    System.exit(0);

                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
            }
        }
    }
}
