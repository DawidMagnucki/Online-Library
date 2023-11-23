package model;

import java.util.Date;

public class Lending {
    Book book;
    Borrower borrower;
    private Date returnDate;

    public Lending(Book book, Borrower borrower){
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

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }
}
