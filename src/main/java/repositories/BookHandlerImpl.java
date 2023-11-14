package repositories;

import exceptions.FilePathNotFoundException;
import model.Book;
import model.BookStatus;
import model.LendingInformation;
import services.Statistics;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import model.Borrower;

public class BookHandlerImpl implements BookHandler {
    public static final String FILE_ACCESS_ERROR_MESSAGE = "An error occurred while saving the book information. File path not found.";
    public static final String SRC_MAIN_RESOURCES_LENDING_TXT = "src/main/resources/lending.txt";
    private static final String DATA_BASE_LIST_OF_BOOKS_TXT = "src/main/resources/Data Base - List of Books.txt";
    private final List<Book> books;


    public BookHandlerImpl() {
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
            throw new FilePathNotFoundException(FILE_ACCESS_ERROR_MESSAGE,
                    exception);
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
            throw new FilePathNotFoundException(FILE_ACCESS_ERROR_MESSAGE,
                    exception);
        }
        return books;
    }

    @Override
    public boolean exists(Book book) {
        return books.stream()
                .anyMatch(existingBook -> existingBook.equals(book));
    }

    @Override
    public void addBookToLendingList(Book book, Borrower borrower, Date date) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SRC_MAIN_RESOURCES_LENDING_TXT, true))) {
            String formattedEntry = String.format("\"%s\" %s - %s - %s - %s", book.getTitle(), book.getAuthor(),
                    book.getBookStatus(), borrower.getId(), date);
            writer.println(formattedEntry);
        } catch (IOException exception) {
            throw new FilePathNotFoundException(FILE_ACCESS_ERROR_MESSAGE,
                    exception);
        }
    }

    public void readLendingList() {

        try (Stream<String> lines = Files.lines(Paths.get(SRC_MAIN_RESOURCES_LENDING_TXT))) {
            List<LendingInformation> lendingInformationList = lines.map(line -> {
                String[] parts = line.split(" - ");
                Book book = new Book(parts[0], parts[1], BookStatus.valueOf(parts[2]));
                Borrower borrower = new Borrower(parts[3], parts[4]);
                return new LendingInformation(book, borrower);
            }).toList();
        } catch (IOException exception) {
            throw new FilePathNotFoundException(FILE_ACCESS_ERROR_MESSAGE,
                    exception);
        }
    }

    public void readLendingListByBorrower(String borrowerId) {

        try (Stream<String> lines = Files.lines(Paths.get(SRC_MAIN_RESOURCES_LENDING_TXT))) {
            List<LendingInformation> lendingInformationList = lines.map(line -> {
                String[] parts = line.split(" - ");
                Book book = new Book(parts[0], parts[1], BookStatus.valueOf(parts[2]));
                Borrower borrower = new Borrower(parts[3], parts[4]);
                return new LendingInformation(book, borrower);
            }).filter(lendingInformation -> lendingInformation.getBorrower().getId().equals(borrowerId))
                    .sorted(Comparator.comparing(lendingInformation -> lendingInformation.getBorrower().getId()))
                    .toList();
        } catch (IOException exception) {
            throw new FilePathNotFoundException(FILE_ACCESS_ERROR_MESSAGE,
                    exception);
        }
    }

    @Override
    public void writeStatisticsData(String fileName, Statistics statistics) {
        // This method writes the statistics data to a file.
    }
}
