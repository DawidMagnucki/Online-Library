package repositories;
import exceptions.FilePathNotFoundException;
import model.Book;
import services.Statistics;
import java.io.*;
import java.util.*;

public class BookHandlerImpl implements BookHandler {
    private final String filePath = "src/main/resources/Data Base - List of Books.txt";
    private final List<Book> books;

    public BookHandlerImpl() {
        books = readAllBooks(); // Read books once at the start
    }

    @Override
    public void saveBookInfo(Book book) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            String formattedEntry = String.format("\"%s\" %s - %s", book.getTitle(), book.getAuthor(), book.getBookStatus());
            writer.println(formattedEntry);
            books.add(book); // Add the book to the list
        } catch (IOException exception) {
            throw new FilePathNotFoundException("An error occurred while saving the book information. File path not found.", exception);
        }
    }

    @Override
    public List<Book> readAllBooks() {
        List<Book> books = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Book book = new Book(line);
                books.add(book);
            }
        } catch (IOException exception) {
            throw new FilePathNotFoundException("An error has occurred while accessing the file. File path not found.", exception);
        }
        return books;
    }

    @Override
    public boolean exists(Book book) {
        return books.contains(book); // Check if the book is in the list
    }

    @Override
    public void writeStatisticsData(String fileName, Statistics statistics) {
        // This method writes the statistics data to a file.
    }
}
