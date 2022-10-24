package controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import dao.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;
import dao.*;
import java.sql.Connection;

@WebServlet(name = "controller/AddMenuServlet", value = "/addMenu")
public class AddMenuServlet extends HttpServlet {
    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Restaurant resto = (Restaurant)session.getAttribute("currentResto");
        MenuManagement me = resto.GetMenuManagement();
        List<Menu> menus = me.GetAllMenus();

        String name = request.getParameter("name");
        String oldName = request.getParameter("oldName");
        String price = request.getParameter("price");
        String calories = request.getParameter("calories");
        String description = request.getParameter("description");
        String ingredients = request.getParameter("ingredients");
        String category = request.getParameter("category");
        String type = request.getParameter("type");

    if(price == null) {
      //Add category
      if(me.IsAvailable(name, false)){
        me.AddCategory(name);
        request.getRequestDispatcher("menuStaffPage.jsp").include(request, response);
      }
      
    }
    else if(type.equals("add")){
      //Add menu
      if(me.IsAvailable(name, true)){
        Menu newMenu = me.AddMenu(name, price, calories, description, ingredients, category);
        if(newMenu != null){
          MenuCategory categoryInstance = me.FindCategoryByName(category);
          categoryInstance.AddItem(newMenu);
          AddMenuInDB(request, response, resto, name, Float.parseFloat(price), Integer.parseInt(calories), "", description, ingredients);
          request.getRequestDispatcher("menuStaffPage.jsp").include(request, response);
        }
      }
    }
    else {
      //Edit menu
      if(me.IsAvailable(name, true)){
        Menu target = null;
        for(Menu menu : menus) {
          if(oldName.equals(menu.GetName())) {
              //find menu
              MenuCategory categoryInstance = me.FindCategoryByName(category);
              menu.EditMenu(name, Float.parseFloat(price), Integer.parseInt(calories),description,ingredients,categoryInstance);
          }
        }
      }
    }

        
        
    }

    private void AddMenuInDB(HttpServletRequest request, HttpServletResponse response, Restaurant resto, String name, float price, int calories, String image, String description, String ingredients ) {
      HttpSession session = request.getSession();
      if (session.getAttribute("manager") == null){
        CreateNewManager(request, response);
      }
      DBManager manager = (DBManager) session.getAttribute("manager");

      try {
          manager.addMenuItem(resto.getRestaurantID(), name, price, calories, image, description, ingredients);
          
          request.getRequestDispatcher("menuStaffPage.jsp").include(request, response);
        } 
        catch (Exception ex) {
            Logger.getLogger(ShowRestaraunts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void CreateNewManager(HttpServletRequest request, HttpServletResponse response) {
        DBConnector db;
        DBManager manager;
        Connection conn;

        try {
            db = new DBConnector();
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();

            conn = db.openConnection();
            manager = new DBManager(conn);
            session.setAttribute("manager", manager);
        } catch (Exception e) {
            System.out.println("Exception is: " + e);
        }
    }

}

