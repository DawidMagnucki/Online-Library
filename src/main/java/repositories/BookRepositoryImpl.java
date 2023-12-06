package repositories;
//TODO: There is bad practice to create such folder as implementations because it's Java inside feature.- D: done
//TODO: Move this files from this folder to Repositories and you can distinct them there - D: done

import exceptions.FilePathNotFoundException;
import model.Book;
import services.Statistics;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    private static final String FILE_ACCESS_ERROR_MESSAGE = "An error occurred while saving the book information. File path not found.";
    private static final String DATA_BASE_LIST_OF_BOOKS_TXT = "Data Base - List of Books.txt";
    private final List<Book> books;


    public BookRepositoryImpl() {
        books = readAllBooks();
    }

    @Override
    public void saveBookInfo(Book book) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_BASE_LIST_OF_BOOKS_TXT, true))) {
            String formattedEntry = String.format("\"%s\" %s - %s", book.getTitle(), book.getAuthor(),
                    book.getBookStatus());
            writer.println(formattedEntry);
            books.add(book);
        } catch (IOException exception) {
            throw new FilePathNotFoundException(FILE_ACCESS_ERROR_MESSAGE
            );
        }
    }

    @Override
    public List<Book> readAllBooks() {
        List<Book> books = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_BASE_LIST_OF_BOOKS_TXT))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Book book = new Book(line);
                books.add(book);
            }
        } catch (IOException exception) {
            throw new FilePathNotFoundException(FILE_ACCESS_ERROR_MESSAGE
            );
        }
        return books;
    }

    @Override
    public boolean exists(Book book) {
        return books.stream()
                .anyMatch(existingBook -> existingBook.equals(book));
    }

    @Override
    public void deleteBook(Book book) {

    }

    @Override
    public void updateBook(Book book) {

    }

    @Override
    public void writeStatisticsData(String fileName, Statistics statistics) {
        // This method writes the statistics data to a file.
    }
}

/* ZADANIE DOMOWE:
 1. ZMIENIĆ NAZWY KLAS BookRepository na BookRepositoryImpl - done
 2. ROZRÓŻNIĆ METODY LENDING OD SAMEJ BOOK, CZYLI STWORZYĆ KOLEJNA KLASĘ LendingRepositoryImpl
 (Jeżeli chcemy stworzyć interface) - done
 3. Uprościć metody (jak wyżej) - done
 4. Popracować nad testami (kody muszą być testowalne)
 5. Dodać metody delete book i update book do interface'u oraz zaimplementuj je w BookHandlerImp, oraz w MySQLBookRepositoryImpl - done
    (na obu metodach możesz użyć preparedStatement) - done
 */