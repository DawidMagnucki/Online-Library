package model;

public class LendingInformation {
    Book book;
    Borrower borrower;

    public LendingInformation (Book book, Borrower borrower){
        this.book = book;
        this.borrower = borrower;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }
}
