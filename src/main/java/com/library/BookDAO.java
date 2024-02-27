package com.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/librarydb";
    private static final String user = "root";
    private static final String password = "Zunaid#70";

    // Database connection method
    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, user, password);

            // Test the connection
            if (connection != null && !connection.isClosed()) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Connection is null or closed!");
            }

            return connection;
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
    }

    // Create a new book
    public void addBook(Book book) {
        String query = "INSERT INTO books (title, author, quantity) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getQuantity());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    // Read all books
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Book book = new Book();
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setQuantity(resultSet.getInt("quantity"));

                books.add(book);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return books;
    }
    
    //get book by title 
    public Book getBookByTitle(String title) {
        String query = "SELECT * FROM books WHERE title=?";
        Book book = new Book();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, title);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    book.setTitle(resultSet.getString("title"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setQuantity(resultSet.getInt("quantity"));
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return book;
    }


    // Update a book
    public void updateBook(Book book) {
        String query = "UPDATE books SET author=?, quantity=? WHERE title=?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, book.getAuthor());
            preparedStatement.setInt(2, book.getQuantity());
            preparedStatement.setString(3, book.getTitle());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    // Delete a book
    public void deleteBook(String title) {
        String query = "DELETE FROM books WHERE title=?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, title);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    // Helper method to handle SQLException and log the error
    private void handleSQLException(SQLException e) {
        // Log the exception using a logging framework (e.g., SLF4J with Logback)
        e.printStackTrace();
        // Handle exceptions appropriately
    }
}
