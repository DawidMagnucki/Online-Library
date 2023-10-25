package repositories;

import model.Book;
import services.Statistics;

import java.util.List;

public interface BookHandler {
    void saveBookInfo(Book book);

    List<Book> readAllBooks();

    boolean exists(Book book);

    void writeStatisticsData(String fileName, Statistics statistics);
}
