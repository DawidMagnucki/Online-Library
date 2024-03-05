package repositories;
import exceptions.FilePathNotFoundException;
import model.Book;
import java.io.*;
import java.util.List;
//TODO: TRY ALL METHODS
//TODO: WRITE TESTS
public class BookRepositoryImpl implements BookRepository {
    private static final String FILE_ACCESS_ERROR_MESSAGE = "An error occurred while saving the book information. File path not found.";
    private static final String LIST_OF_BOOKS_TXT = "List of Books.txt";
    private static final String FORMAT_ENTRY = "\"%s\" %s - %s";
    private final List<Book> books;

    public BookRepositoryImpl() {
        books = getAll();
    }

    @Override
    public void addNew(Book book) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LIST_OF_BOOKS_TXT, true))) {
            String formattedEntry = String.format(FORMAT_ENTRY,
                    book.getTitle(),
                    book.getAuthor(),
                    book.getBookStatus());
            writer.println(formattedEntry);
            books.add(book);
        } catch (IOException exception) {
            throw new FilePathNotFoundException(FILE_ACCESS_ERROR_MESSAGE);
        }
    }

    @Override
    public List<Book> getAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(LIST_OF_BOOKS_TXT))) {
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
    public void update(Book updatedBook) {
        int indexToUpdate = -1;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).equals(updatedBook)) {
                indexToUpdate = i;
                break;
            }
        }
        if (indexToUpdate != -1) {
            books.set(indexToUpdate, updatedBook);
            try (PrintWriter writer = new PrintWriter(new FileWriter(LIST_OF_BOOKS_TXT, false))) {
                for (Book b : books) {
                    String formattedEntry = String.format(FORMAT_ENTRY, b.getTitle(), b.getAuthor(), b.getBookStatus());
                    writer.println(formattedEntry);
                }
            } catch (IOException exception) {
                throw new FilePathNotFoundException(FILE_ACCESS_ERROR_MESSAGE);
            }
        } else {
            System.out.println("The book could not be found.");
        }
    }

    @Override
    public void delete(Book book) {
        books.remove(book);
        try (PrintWriter writer = new PrintWriter(new FileWriter(LIST_OF_BOOKS_TXT, false))) {
            for (Book b : books) {
                String formattedEntry = String.format(FORMAT_ENTRY, b.getTitle(), b.getAuthor(), b.getBookStatus());
                writer.println(formattedEntry);
            }
        } catch (IOException exception) {
            throw new FilePathNotFoundException(FILE_ACCESS_ERROR_MESSAGE);
        }
    }

    @Override
    public boolean exist(Book book) {
        return book != null && books.stream()
                .anyMatch(existingBook -> existingBook.equals(book));
    }
}

