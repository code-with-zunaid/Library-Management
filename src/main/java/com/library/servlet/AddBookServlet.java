package com.library.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.Book;
import com.library.BookDAO;

@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Create a Book object
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setQuantity(quantity);

        // Call the BookDAO to add the book to the database
        BookDAO bookDAO = new BookDAO();
        bookDAO.addBook(book);

        // Redirect to a success page or display a success message
        response.sendRedirect("index.html");
    }
}
