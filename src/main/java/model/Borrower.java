package model;

public class Borrower {

    String name;
    String id;

    public Borrower(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void borrowBook(Book book) {

    }

    public void returnBook(Book book) {

    }

    public void getBorrowedBooks() {

    }

}
