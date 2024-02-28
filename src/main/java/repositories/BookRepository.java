package repositories;
import model.Book;
import java.util.List;

public interface BookRepository {
    void addNew(Book book);

    List<Book> readAllBooks();

    boolean exist(Book book);

    void delete(Book book);

    void update(Book book);

}
