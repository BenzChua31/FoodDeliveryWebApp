package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import java.util.logging.Logger;

import dao.DBConnector;
import dao.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Restaurant;

@WebServlet(name = "controller/MenuUpdateServlet", value = "/menu-update")
public class MenuUpdateServlet extends HttpServlet {

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        System.out.println("entered"); 
        DBManager manager = (DBManager) session.getAttribute("manager");
        if(manager == null) {
            createManager(request, response);
        }
        manager = (DBManager) session.getAttribute("manager");

        try {
            ArrayList<Restaurant> restaraunts = manager.fectRestaraunt();
            System.out.println(restaraunts.size()); 
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println("id: " + id); 

            for (Restaurant restaurant : restaraunts) {
                System.out.println("Resta" + restaurant.getRestaurantID()); 
                if(restaurant.getRestaurantID() == id) {
                    System.out.println("found"); 
                    session.setAttribute("currentResto", restaurant);

                    request.getRequestDispatcher("/menuStaffPage.jsp").include(request, response);
                    return;
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ShowRestaraunts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void createManager(HttpServletRequest request, HttpServletResponse response) {
        DBConnector db;
        DBManager manager;
        Connection conn;

        try {
            db = new DBConnector();
            HttpSession session = request.getSession();

            conn = db.openConnection();
            manager = new DBManager(conn);
            session.setAttribute("manager", manager);
        } catch (Exception e) {
            System.out.println("Exception is: " + e);
        }
    }
}