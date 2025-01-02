
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aqilm
 */
import java.util.List;

class BookManager {
    private LinkedList linkedList = new LinkedList();
    private BookBST bst = new BookBST();

    public void addBook(Book book) {
        linkedList.addBook(book);
        bst.insert(book);
    }

    public boolean removeBook(String isbn) {
        boolean removed = linkedList.removeBook(isbn);
        if (removed) {
            bst.delete(isbn);
        }
        return removed;
    }

    public Book searchBook(String isbn) {
        return bst.search(isbn);
    }

    public List<Book> getAllBooks() {
        return linkedList.toList();
    }
}

