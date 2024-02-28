package repositories;

import model.Book;
import services.Statistics;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MySQLBookRepositoryImpl implements BookRepository {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/online_library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    Scanner scanner = new Scanner(System.in);

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    @Override
    public void addNew(Book book) {
        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO book (TITLE, AUTHOR, STATUS) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, book.getTitle());
                preparedStatement.setString(2, book.getAuthor());
                preparedStatement.setString(3, String.valueOf(book.getBookStatus()));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> readAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            String sql = "SELECT * FROM book";
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");
                    Book book = new Book(title, author, id);
                    books.add(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public boolean exist(Book book) {
        boolean bookExists = false;
        try (Connection connection = getConnection()) {
            String sql = "SELECT COUNT(*) FROM book WHERE TITLE = ? AND AUTHOR = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, book.getTitle());
                preparedStatement.setString(2, book.getAuthor());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        bookExists = count > 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookExists;
    }

    // TODO: Did you test that? You are using , instead of AND - D: done
    @Override
    public void delete(Book book) {
        try (Connection connection = getConnection()) {
            String sql = "DELETE FROM book WHERE TITLE = ? AND AUTHOR = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, book.getTitle());
                preparedStatement.setString(2, book.getAuthor());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TODO: Did you test that? You are using , instead of AND - D: done
    @Override
    public void update(Book book) {
        try (Connection connection = getConnection()) {
            String sql = "UPDATE book SET TITLE = ?, AUTHOR = ?, STATUS = ? WHERE TITLE = ? AND AUTHOR = ? AND STATUS = ?";
            String oldTitle = book.getTitle();
            String oldAuthor = book.getAuthor();
            String oldStatus = String.valueOf(book.getBookStatus());
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
