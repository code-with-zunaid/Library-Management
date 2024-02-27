<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.library.Book"%>
<%@ page import="com.library.BookDAO"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Details</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            flex-direction: column;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        form {
            max-width: 400px;
            width: 100%;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #555;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-bottom: 16px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
        }

        input[type="submit"] {
            background-color: #4caf50;
            color: #fff;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        a {
            text-decoration: none;
            color: #3498db;
            display: block;
            text-align: center;
        }

        a:hover {
            color: #2c3e50;
        }
    </style>
</head>
<body>

    <h2>Book Details</h2>

    <%
        // Get the book title from the request parameter
        String titleToShow = request.getParameter("title");

        // Fetch the book details based on the title
        BookDAO bookDAO = new BookDAO();
        Book bookToShow = bookDAO.getBookByTitle(titleToShow);
    %>

    <% if (bookToShow != null) { %>
        <form action="UpdateBookServlet" method="post">
            <label for="title">Book Title:</label>
            <input type="text" name="title" value="<%= bookToShow.getTitle() %>" readonly>
            
            <label for="author">Author:</label>
            <input type="text" id="author" name="author" value="<%= bookToShow.getAuthor() %>">
            
            <label for="quantity">Quantity:</label>
            <input type="number" id="quantity" name="quantity" value="<%= bookToShow.getQuantity() %>">
            
            <input type="submit" value="Update Book">
        </form>

        <a href="index.html">Back to Home</a>
    <% } else if (request.getMethod().equalsIgnoreCase("post")) { %>
        <p>Book with title '<%= titleToShow %>' not found.</p>
        <a href="index.html">Back to Home</a>
    <% } %>

</body>
</html>
