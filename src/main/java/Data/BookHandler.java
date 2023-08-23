package Data;

import Library.Book;

import java.io.*;
import java.util.*;

public class BookHandler {
    private final String filePath = "C:\\Users\\david\\IdeaProjects\\LibraryProject\\Online-Library\\src\\main\\resources\\Data Base - List of Books.txt";

    public void saveBookInfo(Book book) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            String formattedEntry = String.format("\"%s\" %s - %s", book.getTitle(), book.getAuthor(), book.getBookStatus());
            writer.println(formattedEntry);
        } catch (IOException exception) {
            System.out.println("File not found");
        }
    }

    public List<Book> readAllBooks() { // this method reads the book data from a file
        List<Book> books = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Book book = new Book(line);
                books.add(book);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return books;
    }

    public boolean exists(Book book) {
        List<Book> existingBooks = readAllBooks();
        for (Book existingBook : existingBooks) {
            if (existingBook.equals(book)){
                return true;
            }
        }
        return false;
    }


    public void writeStatisticsData(String fileName, Statistics statistics) { // This method writes the statistics data to a file.

    }
}
