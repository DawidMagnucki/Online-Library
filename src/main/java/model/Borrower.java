package model;

import java.util.ArrayList;
import java.util.List;

public class Borrower {

    private String name;
    private String indexNumber;
    private List<Book> borrowedBooks;

    public Borrower(String name, String indexNumber) {
        this.name = name;
        this.indexNumber = indexNumber;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        System.out.println(name + " has borrowed the book: "
                + book.getTitle() + " by " + book.getAuthor());
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            System.out.println(name + " has returned the book: "
                    + book.getTitle() + " by " + book.getAuthor());
        } else {
            System.out.println(name + " did not borrow the book: "
                    + book.getTitle() + " by " + book.getAuthor());
        }
    }

    public void getBorrowedBooks() {
        System.out.println(name + "'s Borrowed Books:");
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books borrowed.");
        } else {
            for (Book book : borrowedBooks) {
                System.out.println(book.getTitle() + " by " + book.getAuthor());
            }
        }
    }
}
