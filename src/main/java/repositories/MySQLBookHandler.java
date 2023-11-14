package repositories;

import model.Book;
import model.Borrower;
import services.Statistics;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class MySQLBookHandler implements BookHandler {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/online_library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    @Override
    public void saveBookInfo(Book book) {

    }

    @Override
    public List<Book> readAllBooks() {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            String sql = "SELECT * FROM book";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
            }
            resultSet.close();
            statement.close();
            // Close the connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean exists(Book book) {
        return false;
    }

    @Override
    public void addBookToLendingList(Book book, Borrower borrower, Date date) {

    }

    @Override
    public void writeStatisticsData(String fileName, Statistics statistics) {

    }
}
