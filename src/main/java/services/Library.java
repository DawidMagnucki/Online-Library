package services;

import exceptions.BookNotAvailableException;
import model.Book;
import model.BookStatus;
import model.Borrower;
import repositories.BookHandler;
import repositories.BookHandlerImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Library {

    private BookHandler bookHandler;
    Date date = new Date();

    public Library() {
        this.bookHandler = new BookHandlerImpl();
    }

    public void addBook(Book book) { // this method adds a book to the library
        if (!bookHandler.exists(book)) {
            bookHandler.saveBookInfo(book);
            System.out.println("Book added successfully");
        } else {
            System.out.println("Book already exists in the library");
        }
    }

    public void borrowBook(Book book, Borrower borrower) { // this method borrows a book from the
        // library and assigns it to the borrower
 /* 1. Musimy sprawdzić, czy dana książka jest dostępna, czyli czy status jest AVAILABLE
    2. Jeżeli książka nie jest dostępna, to otrzymujemy błąd - the book is not available.
    3. Jeżeli książka jest dostępna, to:
        a) zmieniamy jej status na BORROWED w pliku Data Base - List of Books.txt
        b) dodajemy tę książkę do listy wypożyczonych książek - lending.txt
            - ustawiamy dzisiejszą datę
   * */
        try {
            if (book.getBookStatus().equals(BookStatus.AVAILABLE)) {
                book.setBookStatus(BookStatus.BORROWED);
                bookHandler.addBookToLendingList(book, borrower, date);
            }
        } catch (BookNotAvailableException exception) {
            System.out.println("The book you are trying to borrow is not available. Please try again later.");
        }
    }

    public void returnBook(Book book, Borrower borrower) { // this method returns a book to the library
 /* 1. Metoda będzie sprawdzać, czy data oddania książki nie przekroczyła 30 dni.
    a) jeżeli przekroczyła, to czytelnik musi uiścić opłatę (nie mamy żadnej metody, która by liczyła ilość dni
    od wypożyczenia książki i zmieniała status na DELAYED - jeżeli by tak było, to powyższa metoda nie sprawdzała
    by ilości dni, ale tylko, czy status jest DELAYED czy BORROWED)
    b) jeżeli nie przekroczyła, to status książki zmienia się na AVAILABLE oraz w pliku lending usuwany jest wpis o
    wypożyczeniu.
        */
        Date borrowDate = date;
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

    public void getAvailableBooks() { // this method returns a list of available books
        List<Book> allBooks = getAllBooks();
        List<Book> availableBooks = allBooks.stream()
                .filter(book -> book.getBookStatus() == BookStatus.AVAILABLE)
                .collect(Collectors.toList());

        System.out.println("Available Books:");
        availableBooks.forEach(book -> System.out.println(book.getTitle() + " by " + book.getAuthor()));
    }

    public void getBorrowedBooks() { // this method returns a list of borrowed books
        List<Book> allBooks = getAllBooks();
        List<Book> borrowedBooks = allBooks.stream()
                .filter(book -> book.getBookStatus() == BookStatus.BORROWED)
                .collect(Collectors.toList());

        System.out.println("Borrowed Books:");
        borrowedBooks.forEach(book -> System.out.println(book.getTitle() + " by " + book.getAuthor()));
    }

    public void getStatistics() { // this method returns the statistics data

    }

    public List<Book> getAllBooks() { // this method reads all book info
        return bookHandler.readAllBooks();
    }
}
