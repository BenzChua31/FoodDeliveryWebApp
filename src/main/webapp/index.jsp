
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="controller.*" %>
<%@ page import="model.User" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="model.Restaurant" %>
<%@ page import="model.RCategories" %>
<%@ taglib prefix="c" uri="" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/index.css">
        <title>Main Menu</title>
    </head>

    <%--Temporary Admin User to access all features for demonstration purposes--%>
    <% User u = new User(0, "Admin", "Admin", "admin", "admin@gmail.com", 999, LocalDate.parse("2000-02-13"), 5,
            "Chalter", 2678, "NSW", "Chatsforest", "Australia", true); %>

    <body class="overflow-hidden bg-light">

        <%--NavBar--%>
        <nav class="navbar navbar-expand-lg bg-light mb-3 card">
            <div class="container">
                <a class="h1 navbar-brand text-dark ms-2 mt-2" href="#">FDS</a>
                <div class="float-end me-2">
                    <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="./customerRegister.jsp">Customer Register</a>
                    <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="./staffRegister.jsp">Staff Register</a>
                    <%--Temporary Authentication. Main authentication will come from user login. To demonstrate what a normal customer and appstaff can see.--%>
                    <% if (u.getUserID() == 0) { %>
                        <a class="btn text-dark ms-2 text-decoration-none btn-outline-success" href="update-category">Manage Category</a>
                    <% } %>
                </div>
                <form class="searchf d-flex me-5" role="search">
                    <input class="searchbar form-control me-1" type="search" placeholder="Search" aria-label="Search">
                    <button class="search btn btn-outline-success" type="submit">Search</button>
                </form>
            </div>
        </nav>

        <%--Restaurants--%>
        <section class="container text-center">
            <div class="res row align-items-center justify-content-center">
                <div class="col mt-5">
                    <div class="bs">
                        <img src='images/ofc.png' alt="OFC">
                        <div class="flex-column">
                            <a>Obama Fried Chicken</a>
                            <div class="dropdown">
                                <% RCategories categories;
                                    if (session.getAttribute("categories") == null) {
                                    categories = new RCategories();
                                    session.setAttribute("categories", categories);
                                } else {
                                    categories = (RCategories) session.getAttribute("categories");
                                }%>
                                <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <%-- For demonstration purposes since not interacting with the DBs --%>
                                    <% Restaurant res;
                                        if (session.getAttribute("restaurant") == null) {
                                        res = new Restaurant(4, categories.getCategories().get(0),
                                                "Obama Fried Chicken", 5, "Bal", 6578, "NSW", "Jask",
                                                "Australia", true, 88389491139L, "Robbert", 737487, 335433333333L);
                                        session.setAttribute("restaurant", res);
                                    } else {
                                        res = (Restaurant) session.getAttribute("restaurant");
                                    }%>
                                    <%= res.getCategory().getrCatName() %>
                                </button>
                                <ul class="dropdown-menu">
                                    <%-- disabled to make it unselectable --%>
                                    <c:set var="updateURL">
                                        <c:url value = "update-category">
                                            <c:param name="resID" value="4"/>
                                            <c:param name="cat" value="1"/>
                                        </c:url>
                                    </c:set>
                                    <c:set var="update2URL">
                                        <c:url value = "update-category">
                                            <c:param name="resID" value="4"/>
                                            <c:param name="cat" value="2"/>
                                        </c:url>
                                    </c:set>
                                    <li><a class="dropdown-item disabled" href="${updateURL}">FastFood</a></li>
                                    <li><a class="dropdown-item" href="${update2URL}">SlowFood</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col mt-5">
                    <div class="bs">
                        <img src='images/ofc.png' alt="OFC">
                        <div class="flex-column">
                            <a>Test Fried Chicken</a>
                            <div class="dropdown">
                                <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Test
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item disabled" href="#">Test</a></li>
                                    <li><a class="dropdown-item" href="#">Test</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col mt-5">
                    <div class="bs">
                        <img src='images/ofc.png' alt="OFC">
                        <div class="flex-column">
                            <a>Test Fried Chicken</a>
                            <div class="dropdown">
                                <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Test
                                </button>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item disabled" href="#">Test</a></li>
                                    <li><a class="dropdown-item" href="#">Test</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <%--Initializes DBConnector and sets DBManager in session for use--%>
        <%-- Not needed for prototype --%>
        <jsp:include page="/ConnServlet" flush="true"/>

    </body>

</html>