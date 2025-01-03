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
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(int isbn) {
        return books.removeIf(book -> book.getIsbn() == isbn);
    }

    public List<Book> toList() {
        return books;
    }
}
