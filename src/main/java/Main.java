import model.Book;
import implementations.MySQLBookRepositoryImpl;

public class Main {
    public static void main(String[] args) {
//        Application.start();

      Book book = new Book("ABC", "Agatha Christie");

        MySQLBookRepositoryImpl mySQLBookRepository = new MySQLBookRepositoryImpl();
        mySQLBookRepository.saveBookInfo(book);
        System.out.println(mySQLBookRepository.readAllBooks());

    }
}
