import model.Book;
import repositories.MySQLBookRepositoryImpl;

public class Main {
    public static void main(String[] args) {


        // Application application = new Application();
        // application.start();


        Book book = new Book("ABC", "Agatha Christie");
        Book book1 = new Book("Changed Book", "Unknown");

        MySQLBookRepositoryImpl mySQLBookRepository = new MySQLBookRepositoryImpl();
        mySQLBookRepository.addNew(book);
        System.out.println(mySQLBookRepository.getAll());

        mySQLBookRepository.update(book1);
        System.out.println(mySQLBookRepository.getAll());

        mySQLBookRepository.exist(book);
        System.out.println(mySQLBookRepository.getAll());

        mySQLBookRepository.delete(book1);
        System.out.println(mySQLBookRepository.getAll());


    }
}
