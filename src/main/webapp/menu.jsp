<%@page import="java.util.Iterator"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="controller.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/index.css">
        <title>Order</title>
    </head>
    
    <% 
        ArrayList<MenuItem> menuItems = (ArrayList)session.getAttribute("menuItems");
    %>
    
    <body>
    <table style="width:100%">
        <h1>Show menu List</h1>
    <tr>
        <th>Item Type</th>
        <th>Servings</th>
        <th>Price</th>
        <th>Calories</th>
        <th>Image</th>
        <th>Description</th>
        <th>Ingredients</th>
        <th>Allergy</th>
        <th>ID</th>
        <th>Quantity</th>
        <th>Comment</th>
    </tr>

<%
	//Iterator<MenuItem> iterator = menuItems.iterator();
    MenuManagement me;
    if(session.getAttribute("currentResto") != null){
        Restaurant resto = (Restaurant) session.getAttribute("currentResto");
        me = resto.GetMenuManagement();
    }
    else {
        me = new MenuManagement();
    }
	
    
    List<Menu> menus = me.GetAllMenus();
	//while(iterator.hasNext())  
    for(Menu menu : menus)
	{
		//MenuItem menuItem = iterator.next(); 
	%>
    <tr>
    <td><%=menu.GetName()%></td>
    <td>1</td>
    <td><%=menu.GetPrice()%></td>
    <td><%=menu.getCalories()%></td>
    <td><img src="" width="100" /></td>
    <td><%=menu.getDescription()%></td>
    <td><%=menu.getIngredients()%></td>
    <td>None</td>
    <td>ID?</td>
    <form action="add-menuItem" method="POST">
        <input type="hidden" name="MenuItemID" value="<%= menu.GetName()%>">
        <td><input type="number" name="quantity" value="1"></td>
        <td><textarea name="comment"></textarea></td>
        <td><button type="submit" value="Submit">Add</button></td>
    </form>

    </tr>

    
	<%
	}

%>
</table>

        <a href="delete-order" class="btn btn-dark text-light ms-2"> Delete </a>
        <a href="cart" class="btn btn-dark text-light ms-2"> Cart </a>
    </body>
</html>