package com.library.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.Book;
import com.library.BookDAO;

@WebServlet("/UpdateBookServlet")
public class UpdateBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the book details from the request parameters
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Create a Book object with the updated details
        Book updatedBook = new Book();
        updatedBook.setTitle(title);
        updatedBook.setAuthor(author);
        updatedBook.setQuantity(quantity);

        // Create a BookDAO instance to perform the update
        BookDAO bookDAO = new BookDAO();

        // Update the book
        bookDAO.updateBook(updatedBook);

        // Redirect to viewBooks.jsp after update
        response.sendRedirect("viewBooks.jsp");
    }
}
