package repositories;
import exceptions.FilePathNotFoundException;
import model.Book;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    private static final String FILE_ACCESS_ERROR_MESSAGE = "An error occurred while saving the book information. File path not found.";
    private static final String DATA_BASE_LIST_OF_BOOKS_TXT = "Data Base - List of Books.txt";
    private List<Book> books;

    public BookRepositoryImpl() {
        books = readAllBooks();
    }

    @Override
    public void addNew(Book book) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_BASE_LIST_OF_BOOKS_TXT, true))) {
            String formattedEntry = String.format("\"%s\" %s - %s", book.getTitle(), book.getAuthor(),
                    book.getBookStatus());
            writer.println(formattedEntry);
            books.add(book);
        } catch (IOException exception) {
            throw new FilePathNotFoundException(FILE_ACCESS_ERROR_MESSAGE);
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
            throw new FilePathNotFoundException(FILE_ACCESS_ERROR_MESSAGE);
        }
        return books;
    }

    @Override
    public boolean exist(Book book) {
        return book != null && books.stream()
                .anyMatch(existingBook -> existingBook.equals(book));
    }

    @Override
    public void delete(Book book) {
        books.remove(book);
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_BASE_LIST_OF_BOOKS_TXT, false))) {
            for (Book b : books) {
                String formattedEntry = String.format("\"%s\" %s - %s", b.getTitle(), b.getAuthor(), b.getBookStatus());
                writer.println(formattedEntry);
            }
        } catch (IOException exception) {
            throw new FilePathNotFoundException(FILE_ACCESS_ERROR_MESSAGE);
        }
    }

    @Override
    public void update(Book updatedBook) {
        // Znajdź indeks książki do aktualizacji
        int indexToUpdate = -1;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).equals(updatedBook)) {
                indexToUpdate = i;
                break;
            }
        }

        if (indexToUpdate != -1) {
            books.set(indexToUpdate, updatedBook);
            try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_BASE_LIST_OF_BOOKS_TXT, false))) {
                for (Book b : books) {
                    String formattedEntry = String.format("\"%s\" %s - %s", b.getTitle(), b.getAuthor(), b.getBookStatus());
                    writer.println(formattedEntry);
                }
            } catch (IOException exception) {
                throw new FilePathNotFoundException(FILE_ACCESS_ERROR_MESSAGE);
            }
        } else {
            System.out.println("The book could not be found.");
        }
    }
    }

