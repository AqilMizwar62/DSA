/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */
import java.util.LinkedList;
import java.util.Queue;

class BorrowQueue {
    private Queue<BorrowRequest> queue = new LinkedList<>();

    public void addRequest(String user, int isbn) {
        queue.add(new BorrowRequest(user, isbn));
    }

    public BorrowRequest processNextRequest() {
        return queue.poll(); // Process the next request in FIFO order
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int getQueueSize() {
        return queue.size();
    }
}

class BorrowRequest {
    private String user;
    private int isbn;

    public BorrowRequest(String user, int isbn) {
        this.user = user;
        this.isbn = isbn;
    }

    public String getUser() {
        return user;
    }

    public int getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return "User: " + user + ", ISBN: " + isbn;
    }
}
