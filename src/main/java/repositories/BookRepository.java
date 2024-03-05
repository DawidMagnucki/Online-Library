package repositories;
import model.Book;
import java.util.List;

public interface BookRepository {
    void addNew(Book book);

    List<Book> getAll();

    void update(Book book);

    void delete(Book book);

    boolean exist(Book book);

}
