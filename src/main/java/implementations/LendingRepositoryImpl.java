package implementations;

import exceptions.FilePathNotFoundException;
import model.Book;
import model.BookStatus;
import model.Borrower;
import model.Lending;
import repositories.LendingRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TODO: Not in usage yet. So use it in your code as next task :)
public class LendingRepositoryImpl implements LendingRepository {

    private static final String SRC_MAIN_RESOURCES_LENDING_TXT = "lending.txt";
    private static final String FILE_ACCESS_ERROR_MESSAGE = "An error occurred while saving the book information. File path not found.";

    @Override
    public List<Lending> readLendingList() {
        try (Stream<String> lines = Files.lines(Paths.get(SRC_MAIN_RESOURCES_LENDING_TXT))) {
            return lines.map(this::parseLendingLine).collect(Collectors.toList());
        } catch (IOException exception) {
            throw new FilePathNotFoundException(FILE_ACCESS_ERROR_MESSAGE);
        }
    }

    @Override
    public List<Lending> readLendingListByBorrower(Borrower borrower) {
        return readLendingList()
                .stream()
                .filter(lending -> lending.getBorrower().equals(borrower))
                .collect(Collectors.toList());
    }

    private Lending parseLendingLine(String line) {
        String[] parts = line.split(" - ");
        Book book = new Book(parts[0], parts[1], BookStatus.valueOf(parts[2]));
        Borrower borrower = new Borrower(parts[3], parts[4]);
        return new Lending(book, borrower);
    }

    @Override
    public void addBookToLendingList(Book book, Borrower borrower, Date date) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SRC_MAIN_RESOURCES_LENDING_TXT, true))) {
            String formattedEntry = String.format("\"%s\" %s - %s - %s - %s", book.getTitle(), book.getAuthor(),
                    book.getBookStatus(), borrower.getId(), date);
            writer.println(formattedEntry);
        } catch (IOException exception) {
            throw new FilePathNotFoundException(FILE_ACCESS_ERROR_MESSAGE
            );
        }
    }

    @Override
    public void removeBookFromLendingList(Book book, Borrower borrower) {
        try {
            List<Lending> lendingList = readLendingList();
            List<Lending> updatedLendingList = lendingList.stream()
                    .filter(lending -> !(lending.getBook().equals(book) && lending.getBorrower().equals(borrower)))
                    .collect(Collectors.toList());
            writeLendingListToFile(updatedLendingList);
        } catch (IOException exception) {
            throw new FilePathNotFoundException(FILE_ACCESS_ERROR_MESSAGE);
        }
    }

    @Override
    public void updateLendingDetails(Book book, Borrower borrower, Date newReturnDate) {
        try {
            List<Lending> lendingList = readLendingList();
            lendingList.stream()
                    .filter(lending -> lending.getBook().equals(book) && lending.getBorrower().equals(borrower))
                    .forEach(lending -> lending.setReturnDate(newReturnDate));
            writeLendingListToFile(lendingList);
        } catch (IOException exception) {
            throw new FilePathNotFoundException(FILE_ACCESS_ERROR_MESSAGE);
        }
    }

    private void writeLendingListToFile(List<Lending> lendingList) throws IOException {
        Path lendingFilePath = Paths.get(SRC_MAIN_RESOURCES_LENDING_TXT);
        Files.write(lendingFilePath, lendingList
                .stream()
                .map(Lending::toString)
                .collect(Collectors.toList()));
    }
}
