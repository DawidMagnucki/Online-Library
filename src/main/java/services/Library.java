package services;

import exceptions.BookNotAvailableException;
import model.Book;
import model.BookStatus;
import model.Borrower;
import repositories.BookRepository;
import repositories.BookRepositoryImpl;

import java.util.Date;
import java.util.List;

public class Library {

    private final BookRepository bookRepository;
    //TODO: LendingRepository is not initialized yet. NPE probably in line 40 will be thrown

    public Library() {
        this.bookRepository = new BookRepositoryImpl();
    }

    public void addBook(Book book) {
        if (!bookRepository.exist(book)) {
            bookRepository.addNew(book);
            System.out.println("Book added successfully");
        } else {
            System.out.println("Book already exists in the library");
        }
    }

    public void borrowBook(Book book, Borrower borrower) {
        //TODO: Rename this because we don't know here what kind of date is it
        Date date = new Date();
        try {
            //TODO: New method if(isBookAvailable()){...}
            if (book.getBookStatus().equals(BookStatus.AVAILABLE)) {
                book.setBookStatus(BookStatus.BORROWED);
            }
        } catch (BookNotAvailableException exception) {
            System.out.println("The book you are trying to borrow is not available. Please try again later.");
        }
    }

    public void returnBook(Book book, Borrower borrower) {
        Date borrowDate = new Date();
        Date returnDate = new Date();
        long overdueDays = (returnDate.getTime() - borrowDate.getTime()) / (1000 * 60 * 60 * 24); // obliczamy różnicę w dniach

        if (overdueDays <= 30) {
            book.setBookStatus(BookStatus.AVAILABLE);
            System.out.println("Thank you for returning the book on time.");
        } else {
            book.setBookStatus(BookStatus.DELAYED);
            System.out.println("You have kept this book over 30 days. You need to pay the fee of: PLN " + (2 * overdueDays));
        }
    }

    public void getAvailableBooks() {
        List<Book> allBooks = getAllBooks();
        List<Book> availableBooks = allBooks.stream()
                .filter(book -> book.getBookStatus() == BookStatus.AVAILABLE)
                .toList();

        System.out.println("Available Books:");
        availableBooks.forEach(book -> System.out.println(book.getTitle() + " by " + book.getAuthor()));
    }

    public void getBorrowedBooks() {
        List<Book> allBooks = getAllBooks();
        List<Book> borrowedBooks = allBooks.stream()
                .filter(book -> book.getBookStatus() == BookStatus.BORROWED)
                .toList();

        System.out.println("Borrowed Books:");
        borrowedBooks.forEach(book -> System.out.println(book.getTitle() + " by " + book.getAuthor()));
    }

    public void getStatistics() {

    }

    public List<Book> getAllBooks() { // this method reads all book info
        return bookRepository.getAll();
    }
}
