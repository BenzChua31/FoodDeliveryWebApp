<%@page import="java.util.*"%> 
<%@page import="java.io.*"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="./css/deliveryStatus.css" rel="stylesheet" type="text/css" >
        <link href="./css/header.css" rel="stylesheet" type="text/css" >
        <script src="./js/deliveryStatus.js" defer></script>
        <title>Delivery Status</title>
    </head>
    <body>
        <%
            User user = (User) session.getAttribute("user");
            int orderID = (int) request.getAttribute("orderID");
        %>
        <input id="orderID" value="<%= orderID %>" type="hidden"/>

        <header>
            <div class="header-content">
                <div class="header-start">
                    <a class="header-button" href="index.jsp">Home</a>
                </div>

                <div class="header-end">
                    <div class="user-info header-button">
                        <% if (user != null) { %> 
                            <span>Hello, <%= user.getFname()%></span>
                            <div class="user-menu">
                                <a class="header-button" href="">View Account Details</a>
                                <a class="header-button" href="">View Orders</a>
                                <a class="header-button" href="">Logout</a>
                            </div>
                        <% } else { %>
                            <a href="customerLogin.jsp">Login</a>
                            <span>&nbsp;/&nbsp;</span>
                            <a href="customerRegister.jsp">Register</a>
                        <% } %>
                    </div>
                </div>
            </div>

            <div class="header-outline"></div>
        </header>
                        
        <main>
            <h1>Order no <%= orderID %></h1>
            <div class="delivery">
                <ul class="status">
                    <li>
                        <div class="check-icon">
                            <img src="https://img.icons8.com/color/48/000000/checkmark--v1.png"/>
                        </div>
                        <b>Order received</b>
                    </li>
                    <li>
                        <div class="check-icon">
                            <img 
                                src="https://img.icons8.com/color/48/000000/checkmark--v1.png"
                                id="prepared-img"
                            />
                        </div>
                        <b>Prepared</b>
                    </li>
                    <li>
                        <div class="check-icon">
                            <img 
                                src="https://img.icons8.com/color/48/000000/checkmark--v1.png"
                                id="delivered-img"
                            />
                        </div>
                        <b>Delivered</b>
                    </li>
                </ul>
                <hr />
                <table>
                    <tr>
                        <td>Order Status:</td>
                        <td id="order-status"></td>
                    </tr>
                    <tr>
                        <td>Order type:</td>
                        <td id="order-type"></td>
                    </tr>
                    <tr>
                        <td>Street:</td>
                        <td id="order-street"></td>
                    </tr>
                    <tr>
                        <td>Suburb:</td>
                        <td id="order-suburb"></td>
                    </tr>
                    <tr>
                        <td>State:</td>
                        <td id="order-state"></td>
                    </tr>
                    <tr>
                        <td>Postal:</td>
                        <td id="order-postal"></td>
                    </tr>
                </table>
                <hr />
                <div class="actions">
                    <form action="create-delivery" method="post">
                        <input type="hidden" name="order-to-update" value="<%= orderID %>" />
                        <button type="submit" name="action" class="update-button" value="update">Update</button>
                        <button type="submit" name="action" class="delete-button" value="delete">Delete</button>
                    </form>
                </div>
            </div>
        </main>
    </body>
</html>
