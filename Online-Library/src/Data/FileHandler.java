package Data;
import Library.Book;
import java.io.*;
import java.util.*;

public class FileHandler {
    private String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    public void saveBookInfo(String author, String title) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            String formattedEntry = String.format("\"%s\", %s", title, author);
            writer.println(formattedEntry);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public List<Book> readBookInfo() { // this method reads the book data from a file
        List<Book> books = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Book book = book.parseBookInfo(line);
                if (book != null) {
                    books.add(book);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }


    public void writeStatisticsData(String fileName, Statistics statistics) { // This method writes the statistics data to a file.

    }
}
