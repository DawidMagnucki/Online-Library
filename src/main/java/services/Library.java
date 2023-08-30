package services;

import model.Book;
import model.Borrower;
import repositories.BookHandlerImpl;

import java.util.List;

public class Library {

    private BookHandlerImpl bookHandler = new BookHandlerImpl();



    public void addBook(Book book) { // this method adds a book to the library
        if (!bookHandler.exists(book)) {
            bookHandler.saveBookInfo(book);
            System.out.println("Book added successfully");
        } else {
            System.out.println("Book already exists in the library");
        }
    }

    public void borrowBook(Book book, Borrower borrower) { // this method borrows a book from the library and assign it to the borrower

    }

    public void returnBook(Book book, Borrower borrower) { // this method returns a book to the library

    }

    public void getAvailableBooks() { // this method returns a list of available books

    }

    public void getBorrowedBooks() { // this method returns a list of borrowed books

    }

    public void getStatistics() { // this method returns the statistics data

    }

    public List<Book> getAllBooks() { // this method reads all books info
        return bookHandler.readAllBooks();
    }

}
