import model.Book;
import repositories.MySQLBookRepositoryImpl;

public class Main {
    public static void main(String[] args) {


       // Application application = new Application();
        //application.start();


      Book book = new Book("ABC", "Agatha Christie");

        MySQLBookRepositoryImpl mySQLBookRepository = new MySQLBookRepositoryImpl();
        mySQLBookRepository.addNew(book);
        System.out.println(mySQLBookRepository.readAllBooks());

        mySQLBookRepository.update(book);
        System.out.println(mySQLBookRepository.readAllBooks());


    }
}
