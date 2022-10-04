package controller;

import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import java.util.logging.Logger;

import dao.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.MenuItem;
import model.Order;
import model.OrderItem;
import dao.*;
import java.sql.Connection;

@WebServlet(name = "controller/ShowMenuItems", value = "/show-menuItems")
public class ShowMenuItems extends HttpServlet {

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("manager") == null){
            test(request, response);
        }

        DBManager manager = (DBManager) session.getAttribute("manager");

        try {
            // ArrayList<Restaurant> restaraunts = manager.fectRestaraunt();
            // session.setAttribute("restaraunts", restaraunts);
            // request.getRequestDispatcher("showCIM.jsp").include(request, response);
            Order order = manager.createOrder(1,"Delivery", "Empty");
            session.setAttribute("order", order);
            //OrderItem orderItems = (OrderItem) session.getAttribute("orderItems");
            //System.out.println("hello 2");
            //System.out.println(order.getOrderID());
            ArrayList<MenuItem> menuItems = manager.fectMenuItem();
            session.setAttribute("menuItems", menuItems);
            request.getRequestDispatcher("menu.jsp").include(request, response);
        } 
        catch (SQLException ex) {
            Logger.getLogger(ShowRestaraunts.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private void test(HttpServletRequest request, HttpServletResponse response) {
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
