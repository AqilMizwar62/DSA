
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aqilm
 */
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

class BookManager {
    private LinkedList linkedList = new LinkedList();
    private BookBST bst = new BookBST();
    private BorrowQueue borrowQueue = new BorrowQueue();

    public void addBook(Book book) {
        linkedList.addBook(book);
        bst.insert(book);
    }

    public boolean removeBook(int isbn) {
        boolean removed = linkedList.removeBook(isbn);
        if (removed) {
            bst.delete(isbn);
        }
        return removed;
    }

    public Book searchBook(int isbn) {
        return bst.search(isbn);
    }

    public List<Book> getAllBooks() {
        return linkedList.toList().stream()
                .filter(book -> !book.isBorrowed()) // Only show available books
                .collect(Collectors.toList());
    }

    public void borrowBook(int isbn) {
        Book book = searchBook(isbn);
        if (book != null && !book.isBorrowed()) {
            book.setBorrowed(true); // Mark the book as borrowed
        }
    }

    public void returnBook(int isbn) {
    Book book = searchBook(isbn);
    if (book != null && book.isBorrowed()) {
        book.setBorrowed(false); // Mark the book as available
        // Process the next request in the queue for this book
        processQueueForBook(isbn);
    }
    }

    public void requestBorrow(String user, int isbn) {
        borrowQueue.addRequest(user, isbn);
    }

    public BorrowRequest processBorrowRequest() {
        return borrowQueue.processNextRequest();
    }

    public boolean isBorrowQueueEmpty() {
        return borrowQueue.isEmpty();
    }

    public int getBorrowQueueSize() {
        return borrowQueue.getQueueSize();
    }
    // This method will process the next borrow request when a book is returned.
    private void processQueueForBook(int isbn) {
        BorrowRequest nextRequest = borrowQueue.processNextRequest();
        if (nextRequest != null && nextRequest.getIsbn() == isbn) {
            borrowBook(isbn); // Automatically borrow the book to the next user
            JOptionPane.showMessageDialog(null, "📚 " + nextRequest.getUser() + " can now borrow the book!");
        }
    }    
}


