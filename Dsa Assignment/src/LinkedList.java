/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aqilm
 */
import java.util.ArrayList;
import java.util.List;


class LinkedList {
    private Node head;

    public void addBook(Book book) {
        Node newNode = new Node(book);
        newNode.next = head;
        head = newNode;
    }

    public boolean removeBook(String isbn) {
        Node current = head;
        Node previous = null;

        while (current != null) {
            if (current.book.getIsbn().equals(isbn)) {
                if (previous == null) {
                    head = current.next; // Remove head
                } else {
                    previous.next = current.next; // Remove non-head
                }
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false; // Book not found
    }

    public List<Book> toList() {
        List<Book> books = new ArrayList<>();
        Node current = head;
        while (current != null) {
            books.add(current.book);
            current = current.next;
        }
        return books;
    }
}
