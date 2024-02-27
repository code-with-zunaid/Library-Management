package com.library.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.BookDAO;

@WebServlet("/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the title of the book to delete from the request parameter
        String titleToDelete = request.getParameter("title");

        // Create a BookDAO instance to perform the deletion
        BookDAO bookDAO = new BookDAO();

        // Delete the book
        bookDAO.deleteBook(titleToDelete);

        // Redirect to viewBooks.jsp after deletion
        response.sendRedirect("viewBooks.jsp");
    }
}
