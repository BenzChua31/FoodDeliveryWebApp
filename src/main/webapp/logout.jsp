<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Staff Login</title>
        <link rel="stylesheet" href="css/basic.css">
    </head>
    <% session.invalidate(); %>
    
    <body>
        <h1>You have been logged out</h1>
        <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="./index.jsp">Go to Index Page</a>
    </body>
</html>