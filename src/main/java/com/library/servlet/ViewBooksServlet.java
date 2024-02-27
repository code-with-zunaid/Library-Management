package com.library.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.Book; // Correct import statement
import com.library.BookDAO;

@WebServlet("/ViewBooksServlet")
public class ViewBooksServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Call the BookDAO to retrieve the list of books from the database
        BookDAO bookDAO = new BookDAO();
        List<Book> books = bookDAO.getAllBooks();

        // Set the list of books as an attribute in the request
        request.setAttribute("books", books);

        // Forward the request to viewBooks.jsp for displaying the list of books
        request.getRequestDispatcher("viewBooks.jsp").forward(request, response);
    }
}
