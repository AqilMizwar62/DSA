/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aqilm
 */
class BookBST {
    private BSTNode root;

    public void insert(Book book) {
        root = insertRec(root, book);
    }

    private BSTNode insertRec(BSTNode root, Book book) {
        if (root == null) {
            return new BSTNode(book);
        }
        if (book.getIsbn().compareTo(root.book.getIsbn()) < 0) {
            root.left = insertRec(root.left, book);
        } else if (book.getIsbn().compareTo(root.book.getIsbn()) > 0) {
            root.right = insertRec(root.right, book);
        }
        return root;
    }

    public Book search(String isbn) {
        return searchRec(root, isbn);
    }

    private Book searchRec(BSTNode root, String isbn) {
        if (root == null) {
            return null; // Book not found
        }
        if (isbn.equals(root.book.getIsbn())) {
            return root.book;
        }
        if (isbn.compareTo(root.book.getIsbn()) < 0) {
            return searchRec(root.left, isbn);
        } else {
            return searchRec(root.right, isbn);
        }
    }

    public void delete(String isbn) {
        root = deleteRec(root, isbn);
    }

    private BSTNode deleteRec(BSTNode root, String isbn) {
        if (root == null) {
            return null;
        }
        if (isbn.compareTo(root.book.getIsbn()) < 0) {
            root.left = deleteRec(root.left, isbn);
        } else if (isbn.compareTo(root.book.getIsbn()) > 0) {
            root.right = deleteRec(root.right, isbn);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.book = findMin(root.right).book;
            root.right = deleteRec(root.right, root.book.getIsbn());
        }
        return root;
    }

    private BSTNode findMin(BSTNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
}
