package repositories;

import model.Book;
import model.Borrower;
import services.Statistics;

import java.util.Date;
import java.util.List;

public interface BookHandler {
    void saveBookInfo(Book book);

    List<Book> readAllBooks();

    boolean exists(Book book);

    void addBookToLendingList(Book book, Borrower borrower, Date date);

    void writeStatisticsData(String fileName, Statistics statistics);
}
