package repositories;

import model.Book;
import model.Borrower;
import repositories.BookRepository;
import services.Statistics;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

// TODO: Try to take similar code into common method - D: I don't understant what code in what method? (czyli muszę wspólne bloki kodów przerobić na metody, a później ich użyć)
// TODO: Use try-with-resources (czyli try () a nie try {})
public class MySQLBookRepositoryImpl implements BookRepository {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/online_library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    Scanner scanner = new Scanner(System.in);


    @Override
    public void saveBookInfo(Book book) {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String sql = "INSERT INTO book (TITLE, AUTHOR, STATUS) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, String.valueOf(book.getBookStatus()));
            preparedStatement.executeUpdate();
            // Close the connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> readAllBooks() {
        List<Book> books = new ArrayList<>();
        try {
            try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                 Statement statement = connection.createStatement()) {

                String sql = "SELECT * FROM book";
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");
                    Book book = new Book(title, author, id);
                    books.add(book);
                }
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public boolean exists(Book book) {
        boolean bookExists = false;

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String sql = "SELECT COUNT(*) FROM book WHERE TITLE = ? AND AUTHOR = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                bookExists = count > 0;
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookExists;
    }
    // TODO: Did you test that? You are using , instead of AND - D: done
    @Override
    public void deleteBook(Book book) {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String sql = "DELETE FROM book WHERE TITLE = ? AND AUTHOR = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TODO: Did you test that? You are using , instead of AND - D: To be updated. Not working well.
    @Override
    public void updateBook(Book book) {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);


            String sql = "UPDATE book SET TITLE = ? AND AUTHOR = ? AND STATUS = ? WHERE TITLE = ? AND AUTHOR = ? AND STATUS = ?";
            String oldTitle = book.getTitle();
            String oldAuthor = book.getAuthor();
            String oldStatus = String.valueOf(book.getBookStatus());
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.println("Please provide new title of the book you would like to update");
            String newTitle = scanner.nextLine();
            preparedStatement.setString(1, newTitle);
            System.out.println("Please provide new author of the book you would like to update");
            String newAuthor = scanner.nextLine();
            preparedStatement.setString(2, newAuthor);
            System.out.println("Please provide new status of the book you would like to update");
            String newStatus = String.valueOf(scanner.nextLine());
            preparedStatement.setString(3, newStatus);
            preparedStatement.setString(4, oldTitle);
            preparedStatement.setString(5, oldAuthor);
            preparedStatement.setString(6, oldStatus);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeStatisticsData(String fileName, Statistics statistics) {

    }
}

