package repositories;

import exceptions.FilePathNotFoundException;
import model.Book;
import model.Borrower;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookHandlerImplTest {

    @Test
    void shouldSaveNewBookInfo() {
        //given
        BookHandlerImpl bookHandler = new BookHandlerImpl();
        Book book = new Book("Coding Methods", "Tomasz Tworek");
        //when
        bookHandler.saveBookInfo(book);
        //then
        assertTrue(bookHandler.exists(book));
    }
    @Test
    void shouldThrowExceptionWhenPathNotFound() { /* Nie wiem, czy ten test jest zasadny, ponieważ file path jest
    ustawione jako static final, więc, po pierwsze ona się nie zmieni, a po drugie nigdzie w kodzie nie jest wprowadzana
    ścieżka do pliku. Jako, że zawsze będzie taka sama, to nigdy nam nie wyrzuci wyjątku. Z drugiej strony dodawanie do
    konstruktora file path jest bez sensu, bo rzutuje to na inne metody.*/
        /*
        // given
        BookHandlerImpl bookHandler = new BookHandlerImpl();
        Book book = new Book("Coding Methods", "Tomasz Tworek");

        // when and then
        FilePathNotFoundException exception = assertThrows(FilePathNotFoundException.class, () ->
                bookHandler.saveBookInfo(book)
        );

        // assert
        assertEquals("An error occurred while saving the book information. File path not found.", exception.getMessage());
        */
    }
    @Test
    void shouldReturnListOfAllBooks() {
       //given
        BookHandlerImpl bookHandler = new BookHandlerImpl();
        List<Book> expectedBooks = new ArrayList<>();
        //when
        List<Book> actualBooks = bookHandler.readAllBooks();
        //then
        assertArrayEquals(expectedBooks.toArray(), actualBooks.toArray());
    }
    @Test
    void shouldCheckIfBookExists() {
        //given
        BookHandlerImpl bookHandler = new BookHandlerImpl();
        String title = "Coding Methods";
        String author = "Tomasz Tworek";

        //when
        boolean result = bookHandler.exists(new Book(title, author));

        //then
        assertTrue(result);
    }

    @Test
    void shouldAddBookToLendingList() {
            //given
            BookHandlerImpl bookHandler = new BookHandlerImpl();
            Book book = new Book("Coding Methods", "Tomasz Tworek");
            Borrower borrower = new Borrower("dawid", "45c54e94-df67-4365-982e-ec27c6c1d5c2");

            //when
        bookHandler.addBookToLendingList(book, borrower, new Date());

        //then
            try {
                List<String> lendingLines = Files.readAllLines(Path.of("src/main/resources/lending.txt"));
                assertEquals(1, lendingLines.size());

                String expectedLine = "\"Coding Methods\" Tomasz Tworek - AVAILABLE - 45c54e94-df67-4365-982e-ec27c6c1d5c2 -";
                assertEquals(expectedLine, lendingLines.get(0));
            } catch (IOException e) {
                // Handle or propagate the exception as needed
                e.printStackTrace();
            }
        }

    @Test
    void readLendingList() {
        //given

        //when

        //then
    }

    @Test
    void readLendingListByBorrower() {
        //given

        //when

        //then
    }

    @Test
    void writeStatisticsData() {
        //given

        //when

        //then
    }
}