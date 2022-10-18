package dao;

import java.sql.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

import model.AppStaff;
import model.Customer;
import model.Delivery;
import model.DeliveryDriver;
import model.MenuItem;
import model.Order;
import model.OrderItem;
import model.Restaurant;
import model.Staff;
import model.User;


public class DBManager {

    private Statement st;
    private PreparedStatement ps;
    private String fetch;
    private Connection conn;
   
    public DBManager(Connection conn) throws SQLException {       
        st = conn.createStatement();  
//        ps = conn.prepareStatement(); 
        this.conn = conn;
    }
/* 
    public void testAdder(String firstName, String lastName) {
        fetch = "INSERT INTO db.tables.Customer " + "VALUES (" + firstName + ", '" + lastName + "')";
        System.out.println(fetch);
        ps.executeUpdate();

    }
*/
    public void addUser(String firstName, String lastName, String password, String email, int phoneNumber, String dob, int streetNumber, String streetName, int postcode, String state, String suburb, String country, boolean activated) throws SQLException {
       // fetch = "INSERT INTO db.user " + "VALUES (" + 000123 + ",'" + firstName + "', '" + lastName + "', '" + password + "', '" + email + "', '" + phoneNumber + "', '" + dob + "', '" + streetNumber + "', '" + streetName + "', '" + postcode + "', '" + state + "', '" + suburb + "', '" + country + "', " + 1 + ")";
       // System.out.println("made it to addUser in dbmanager");
        
        fetch = "insert into db.user(first_name, last_name, password, email, phoneNo, dob, street_number, street_name, postcode, state, suburb, country, activated) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
 
        PreparedStatement ps = conn.prepareStatement(fetch);
        ps.setString(1, firstName);
        ps.setString(2, lastName);
        ps.setString(3, password);
        ps.setString(4, email);
        ps.setInt(5, phoneNumber);
        ps.setString(6, dob);
        ps.setInt(7, streetNumber);
        ps.setString(8, streetName);
        ps.setInt(9, postcode);
        ps.setString(10, state);
        ps.setString(11, suburb);
        ps.setString(12, country);
        ps.setBoolean(13, activated);
        System.out.println(fetch);
        ps.executeUpdate();

    }

    public void addStaff(String firstName, String lastName, String password, String email, String phoneNumber, String dob, String streetNumber, String streetName, String postcode, String state, String suburb, String country, boolean activated, int privilege, String position) throws SQLException {
        String fetch = "INSERT INTO db.tables.Staff " + "VALUES (" + firstName + ", '" + lastName + "', '" + password + "', '" + email + "', '" + phoneNumber + "', '" + dob + "', '" + streetNumber + "', '" + streetName + "', '" + postcode + "', '" + state + "', '" + suburb + "', '" + country + "', '" + activated + "', '" + privilege + "', '" + position + "')";
        
        System.out.println(fetch);
    }

    public Customer findCustomer(String Cemail, String Cpassword) throws SQLException {

        String fetch = "select * from db.user where email='" + Cemail + "' and Password= '" + Cpassword + "'";
        ResultSet rs = st.executeQuery(fetch);
        
        if (!rs.next()) {
            return null;
        }

        //rs.next();
        String email = rs.getString(5);
        String password = rs.getString(4);

        if (email.equals(Cemail) && password.equals(Cpassword)) {
            System.out.println("Customer Found!!");
            int userID = rs.getInt(1);
            String firstName = rs.getString(2);
            String lastName = rs.getString(3);
            password = rs.getString(4);
            email = rs.getString(5);
            int phone = rs.getInt(6);
            
            String dateOfBirth = rs.getString(7);
            LocalDate localDate = LocalDate.parse(dateOfBirth);
            int streetNumber = rs.getInt(8);
            String streetName = rs.getString(9);
            int postcode = rs.getInt(10);
            String state = rs.getString(11);
            String suburb = rs.getString(12);
            String country = rs.getString(13);
            boolean activated = rs.getBoolean(14);
            
            fetch = "select * from db.customer where user_ID=" + userID;
            rs = st.executeQuery(fetch);
            rs.next();

            int customerID = rs.getInt(1);
            String cardNumber = rs.getString(3);
            String cardExpiration = rs.getString(4);
            LocalDate localDate2 = LocalDate.parse(cardExpiration);
            int cardPin = rs.getInt(5);
            String cardName = rs.getString(6);
            return new Customer(userID, firstName, lastName, password, email, phone, localDate, streetNumber, streetName, postcode, state, suburb, country, activated, customerID, cardNumber, localDate2, cardPin, cardName);
            
        }

        else {
           System.out.println("DBMANAGER USER NOT FOUND FROM FINDUSER METHOD");
            return null; 
        }
        
        
    }

    public void addPaymentDetails(int userID, int cardNumber, String cardExpiration, int cardPin, String cardName) throws SQLException {
        // find the user and get their user id 

        fetch = "insert into db.customer(user_id, card_number, card_expiration, card_pin, card_name) values(?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(fetch);
        ps.setInt(1, userID);
        ps.setInt(2, cardNumber);
        ps.setString(3, cardExpiration);
        ps.setInt(4, cardPin);
        ps.setString(5, cardName);

        ps.executeUpdate();

    }

    public User findUser(String email, String password) throws SQLException{
        String fetch = "select * from db.user where email='" + email + "' and Password= '" + password + "'";
        ResultSet rs = st.executeQuery(fetch);
   
        while (rs.next()) {
            String custEmail = rs.getString(5);
            String custPassword = rs.getString(4);
            if (custEmail.equals(email) && custPassword.equals(password)) {
                System.out.println("Customer Found!!");
                int userID = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                custPassword = rs.getString(4);
                custEmail = rs.getString(5);
                int phone = rs.getInt(6);
                
                String dateOfBirth = rs.getString(7);
                LocalDate localDate = LocalDate.parse(dateOfBirth);
                
                int streetNumber = rs.getInt(8);
                String streetName = rs.getString(9);
                int postcode = rs.getInt(10);
                String state = rs.getString(11);
                String suburb = rs.getString(12);
                String country = rs.getString(13);
                boolean activated = rs.getBoolean(14);
                
                return new User(userID, firstName, lastName, custPassword, custEmail, phone, localDate, streetNumber, streetName, postcode, state, suburb, country, activated);
                
            }
        }

        return null;
    }

    public void addStaffDetails(String email, int restaurantID, int privilege, String position) throws SQLException{
        fetch = "SELECT * FROM `User` WHERE Email='"+ email + "';";
        ResultSet rs = st.executeQuery(fetch);
        rs.next();
        int userID = rs.getInt(1);

        fetch = "insert into db.staff(userID, restaurant_ID, privilege, position) values(?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(fetch);
        ps.setInt(1, userID);
        ps.setInt(2, restaurantID);
        ps.setInt(3, privilege);
        ps.setString(4, position);

        ps.executeUpdate();
    }

    public void addPaymentDetails(String email, String cardNumber, String cardExpiration, int cardPin, String cardName) throws SQLException {
        // find the user and get their user id 
        System.out.println(email + " is the email being passed into the addPaymentDetails method in manager");
        fetch = "SELECT * FROM `User` WHERE Email='"+ email + "';";
        ResultSet rs = st.executeQuery(fetch);
        rs.next();
        int userID = rs.getInt(1);

        fetch = "insert into db.customer(user_id, card_number, card_expiration, card_pin, card_name) values(?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(fetch);
        ps.setInt(1, userID);
        ps.setString(2, cardNumber);
        ps.setString(3, cardExpiration);
        ps.setInt(4, cardPin);
        ps.setString(5, cardName);

        ps.executeUpdate();

    }
/* 
    public User findUser(String email, String password) throws SQLException{
        String fetch = "select * from db.user where email='" + email + "' and Password= '" + password + "'";
        ResultSet rs = st.executeQuery(fetch);
   
        while (rs.next()) {
            String custEmail = rs.getString(5);
            String custPassword = rs.getString(4);
            if (custEmail.equals(email) && custPassword.equals(password)) {
                System.out.println("Customer Found!!");
                int userID = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                custPassword = rs.getString(4);
                custEmail = rs.getString(5);
                int phone = rs.getInt(6);
                
                String dateOfBirth = rs.getString(7);
                LocalDate localDate = LocalDate.parse(dateOfBirth);
                
                int streetNumber = rs.getInt(8);
                String streetName = rs.getString(9);
                int postcode = rs.getInt(10);
                String state = rs.getString(11);
                String suburb = rs.getString(12);
                String country = rs.getString(13);
                boolean activated = rs.getBoolean(13);
                
                return new User(userID, firstName, lastName, custPassword, custEmail, phone, localDate, streetNumber, streetName, postcode, state, suburb, country, activated);
                
            }
        }

        return null;
    }
/* 
    public void addStaffDetails(String email, int restaurantID, int privilege, String position) throws SQLException{
        fetch = "SELECT * FROM `User` WHERE Email='"+ email + "';";
        ResultSet rs = st.executeQuery(fetch);
        rs.next();
        int userID = rs.getInt(1);

        fetch = "insert into db.staff(userID, restaurant_ID, privilege, position) values(?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(fetch);
        ps.setInt(1, userID);
        ps.setInt(2, restaurantID);
        ps.setInt(3, privilege);
        ps.setString(4, position);

        ps.executeUpdate();
    }
*/
    public void updateCustomer(int userID, String firstName, String lastName, String password, String email, String phone, String dateOfBirth, int streetNumber, String streetName, int postcode, String state, String suburb, String country, boolean activated, int customerID, int cardNumber, String cardExpiration, int cardPin, String cardName) throws SQLException {
        
        fetch = "UPDATE db.user SET first_name = ?, last_name = ?, password = ?, email = ?, phoneNo = ?, dob = ?, street_number = ?, street_name = ?, postcode = ?, state = ?, suburb = ?, country = ?, activated = ? WHERE userID = ?";
        PreparedStatement ps = conn.prepareStatement(fetch);
        ps.setString(1, firstName);
        ps.setString(2, lastName);
        ps.setString(3, password);
        ps.setString(4, email);
        ps.setString(5, phone);
        ps.setString(6, dateOfBirth);
        ps.setInt(7, streetNumber);
        ps.setString(8, streetName);
        ps.setInt(9, postcode);
        ps.setString(10, state);
        ps.setString(11, suburb);
        ps.setString(12, country);
        ps.setInt(13, 1);
        ps.setInt(14, userID);

        System.out.println(fetch);
        ps.executeUpdate();
        System.out.println(ps);

        fetch = "UPDATE db.customer SET card_number = ?, card_expiration = ?, card_pin = ?, card_name = ? WHERE user_id = ?";
        ps = conn.prepareStatement(fetch);
        ps.setInt(1, cardNumber);
        ps.setString(2, cardExpiration);
        ps.setInt(3, cardPin);
        ps.setString(4, cardName);
        ps.setInt(5, userID);

        System.out.println(ps);
        ps.executeUpdate();
        

        /*
         * UPDATE Customers
         * SET ContactName = 'Alfred Schmidt', City= 'Frankfurt'
         * WHERE CustomerID = 1;
         */
    }

    public Customer findCustomer(int CuserID) throws SQLException {
        String fetch = "select * from db.user where UserID=" + CuserID;
        ResultSet rs = st.executeQuery(fetch);
   
        rs.next();
        String email = rs.getString(5);
        String password = rs.getString(4);
        int userID = rs.getInt(1);
        String firstName = rs.getString(2);
        String lastName = rs.getString(3);
        password = rs.getString(4);
        email = rs.getString(5);
        int phone = rs.getInt(6);
        
        String dateOfBirth = rs.getString(7);
        LocalDate localDate = LocalDate.parse(dateOfBirth);
        int streetNumber = rs.getInt(8);
        String streetName = rs.getString(9);
        int postcode = rs.getInt(10);
        String state = rs.getString(11);
        String suburb = rs.getString(12);
        String country = rs.getString(13);
        boolean activated = rs.getBoolean(14);
        
        fetch = "select * from db.customer where user_ID=" + userID;
        rs = st.executeQuery(fetch);
        rs.next();
        int customerID = rs.getInt(1);
        String cardNumber = rs.getString(3);
        String cardExpiration = rs.getString(4);
        LocalDate localDate2 = LocalDate.parse(cardExpiration);
        int cardPin = rs.getInt(5);
        String cardName = rs.getString(6);
        return new Customer(userID, firstName, lastName, password, email, phone, localDate, streetNumber, streetName, postcode, state, suburb, country, activated, customerID, cardNumber, localDate2, cardPin, cardName);

    } 

    public Staff findStaff(String Semail, String Spassword) throws SQLException {

        String fetch = "select * from db.user where email='" + Semail + "' and Password= '" + Spassword + "'";
        ResultSet rs = st.executeQuery(fetch);
   
        if (!rs.next()) {
            return null;
        }
     //   rs.next();
        String email = rs.getString(5);
        String password = rs.getString(4);

        if (email.equals(Semail) && password.equals(Spassword)) {
            System.out.println("Customer Found!!");
            int userID = rs.getInt(1);
            String firstName = rs.getString(2);
            String lastName = rs.getString(3);
            password = rs.getString(4);
            email = rs.getString(5);
            int phone = rs.getInt(6);
            
            String dateOfBirth = rs.getString(7);
            LocalDate localDate = LocalDate.parse(dateOfBirth);
            int streetNumber = rs.getInt(8);
            String streetName = rs.getString(9);
            int postcode = rs.getInt(10);
            String state = rs.getString(11);
            String suburb = rs.getString(12);
            String country = rs.getString(13);
            boolean activated = rs.getBoolean(14);
            
            fetch = "select * from db.staff where userID=" + userID;
            System.out.println(fetch);
            rs = st.executeQuery(fetch);
            if (!rs.next()) {
                return null;
            }
            //rs.next();

            int staffID = rs.getInt(1);
            int restaurantID = rs.getInt(3);
            int privilege = rs.getInt(4);
            String position = rs.getString(5);
            return new Staff(userID, firstName, lastName, password, email, phone, localDate, streetNumber, streetName, postcode, state, suburb, country, activated, staffID, restaurantID, privilege, position);
            
        }

        else {
           System.out.println("DBMANAGER STAFF NOT FOUND FROM FINDUSER METHOD");
            return null; 
        }
        
        
    }

    public void updateStaff(int userID, String firstName, String lastName, String password, String email, int phone, String dateOfBirth, int streetNumber, String streetName, int postcode, String state, String suburb, String country, boolean activated, int staffID, int restauranID, int privilege, String position) throws SQLException {
        
        fetch = "UPDATE db.user SET first_name = ?, last_name = ?, password = ?, email = ?, phoneNo = ?, dob = ?, street_number = ?, street_name = ?, postcode = ?, state = ?, suburb = ?, country = ?, activated = ? WHERE userID = ?";
        PreparedStatement ps = conn.prepareStatement(fetch);
        ps.setString(1, firstName);
        ps.setString(2, lastName);
        ps.setString(3, password);
        ps.setString(4, email);
        ps.setInt(5, phone);
        ps.setString(6, dateOfBirth);
        ps.setInt(7, streetNumber);
        ps.setString(8, streetName);
        ps.setInt(9, postcode);
        ps.setString(10, state);
        ps.setString(11, suburb);
        ps.setString(12, country);
        ps.setInt(13, 1);
        ps.setInt(14, userID);

        System.out.println(fetch);
        ps.executeUpdate();
        System.out.println(ps);

        fetch = "UPDATE db.staff SET restaurant_id = ?, privilege = ?, position = ? WHERE userID = ?";
        ps = conn.prepareStatement(fetch);
        ps.setInt(1, restauranID);
        ps.setInt(2, privilege);
        ps.setString(3, position);
        ps.setInt(4, userID);

        System.out.println(ps);
        ps.executeUpdate();

        /*
         * UPDATE Customers
         * SET ContactName = 'Alfred Schmidt', City= 'Frankfurt'
         * WHERE CustomerID = 1;
         */
    }

    public Staff findStaff(int SuserID) throws SQLException {

        String fetch = "select * from db.user where UserID=" + SuserID;
        ResultSet rs = st.executeQuery(fetch);
   
        rs.next();
        String email = rs.getString(5);
        String password = rs.getString(4);
        int userID = rs.getInt(1);
        String firstName = rs.getString(2);
        String lastName = rs.getString(3);
        password = rs.getString(4);
        email = rs.getString(5);
        int phone = rs.getInt(6);
        
        String dateOfBirth = rs.getString(7);
        LocalDate localDate = LocalDate.parse(dateOfBirth);
        int streetNumber = rs.getInt(8);
        String streetName = rs.getString(9);
        int postcode = rs.getInt(10);
        String state = rs.getString(11);
        String suburb = rs.getString(12);
        String country = rs.getString(13);
        boolean activated = rs.getBoolean(14);
        
        fetch = "select * from db.staff where userID=" + userID;
        rs = st.executeQuery(fetch);
        rs.next();
        int staffID = rs.getInt(1);
        int restaurantID = rs.getInt(3);
        int privilege = rs.getInt(4);
        String position = rs.getString(5);
        return new Staff(userID, firstName, lastName, password, email, phone, localDate, streetNumber, streetName, postcode, state, suburb, country, activated, staffID, restaurantID, privilege, position);
    }

    public boolean validStaffRes(int staffID, int restaurantID) {
        try {
            ResultSet rs = st.executeQuery(
                    "SELECT * FROM STAFF WHERE STAFF_ID = " + staffID + " AND RESTAURANT_ID = " + restaurantID);
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Exception is: " + e);
        }
        return false;
    }

    // Order
    public Order getOrder(int orderID) {
        System.out.println("Got here");
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM db.Order WHERE Order_ID = " + orderID);
            if (rs.next()) {
                return new Order(
                        rs.getInt("ORDER_ID"),
                        rs.getInt("CUSTOMER_ID"),
                        rs.getInt("RESTAURANT_ID"),
                        rs.getString("ORDER_TYPE"),
                        rs.getInt("COUPON_ID"),
                        rs.getString("STATUS"),
                        rs.getInt("FOOD_RATING"),
                        rs.getString("FOOD_INSTRUCTIONS"),
                        rs.getString("FOOD_FEEDBACK"));
            }
        } catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Exception in getOrder() is: " + e);
        }
        return null;
    }

    public ArrayList<Order> getOrdersByResID(int restaurantID) {
        try {
            ArrayList<Order> orders = new ArrayList<Order>();
            ResultSet rs = st.executeQuery("SELECT * FROM db.Order " +
                    " WHERE Restaurant_ID = " + restaurantID +
                    " AND (Status = 'Order Received' OR Status = 'Preparing')");
            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("ORDER_ID"),
                        rs.getInt("CUSTOMER_ID"),
                        rs.getInt("RESTAURANT_ID"),
                        rs.getString("ORDER_TYPE"),
                        rs.getInt("COUPON_ID"),
                        rs.getString("STATUS"),
                        rs.getInt("FOOD_RATING"),
                        rs.getString("FOOD_INSTRUCTIONS"),
                        rs.getString("FOOD_FEEDBACK")));
            }
            return orders;
        } catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Exception in getOrdersByResID() is: " + e);
        }
        return null;
    }

    public boolean updateOrder(Order order) {
        try {
            st.executeUpdate("UPDATE db.Order SET" +
                    " Order_ID = " + order.getOrderID() + "," +
                    " Customer_ID = " + order.getCustomerID() + "," +
                    " Restaurant_ID = " + order.getRestaurantID() + "," +
                    " Order_Type = '" + order.getOrderType() + "'," +
                    " Coupon_ID = " + (order.getCouponID() == 0 ? "NULL" : order.getCouponID()) + "," +
                    " Status = '" + order.getStatus() + "'," +
                    " Food_Rating = " + (order.getFoodRating() == 0 ? "NULL" : order.getFoodRating()) + "," +
                    " Food_Instructions = "
                    + (order.getFoodInstructions() == null ? "NULL" : "'" + order.getFoodInstructions() + "'") + "," +
                    " Food_Feedback = " +
                    (order.getFoodFeedback() == null ? "NULL" : "'" + order.getFoodFeedback() + "'") +
                    " WHERE Order_ID = " + order.getOrderID());

            return true;
        } catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Exception in updateOrder() is: " + e);
            return false;
        }

    }

    public boolean deleteOrder(int orderID) {
        try {
            st.executeUpdate("DELETE db.Order, Order_Item" +
                    " FROM db.Order INNER JOIN Order_Item ON db.Order.Order_ID = Order_Item.Order_ID" +
                    " WHERE db.Order.Order_ID = " + orderID);
            return true;
        } catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Exception in deleteOrder() is: " + e);
            return false;
        }
    }

    // OrderItem
    public ArrayList<OrderItem> getOrderItems(int orderID) {
        try {
            ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
            ResultSet rs = st.executeQuery("SELECT * FROM ORDER_ITEM WHERE ORDER_ID = " + orderID);
            while (rs.next()) {
                orderItems.add(new OrderItem(
                        rs.getInt("ORDER_ID"),
                        rs.getInt("ITEM_ID"),
                        rs.getInt("QUANTITY"),
                        rs.getString("COMMENT")));
            }
            return orderItems;
        } catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Exception is: " + e);
        }
        return null;
    }

    // Menu_Item
    public MenuItem getMenuItem(int itemID) {
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM MENU_ITEM WHERE ITEM_ID = " + itemID);
            while (rs.next()) {
                MenuItem menuItem = new MenuItem(
                        rs.getInt("ITEM_ID"),
                        rs.getInt("RESTAURANT_ID"),
                        rs.getString("ITEM_TYPE"),
                        rs.getInt("SERVINGS"),
                        rs.getFloat("PRICE"),
                        rs.getInt("CALORIES"),
                        rs.getString("IMAGE"),
                        rs.getString("DESCRIPTION"),
                        rs.getString("INGREDIENTS"),
                        rs.getString("ALLERGY"),
                        rs.getInt("STOCK"));
                return menuItem;
            }

        } catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Exception is: " + e);
        }
        return null;
    }

    // Delivery
    public boolean createDelivery(Delivery delivery) {
        try {
            st.executeUpdate(
                    "INSERT INTO DELIVERY(ORDER_ID, DRIVER_ID, DELIVERY_STREET, DELIVERY_SUBURB, DELIVERY_STATE, DELIVERY_POSTAL, DELIVERY_FEE, DRIVER_INSTRUCTIONS) VALUES ("
                            + delivery.getOrderID() + ", NULL, '"
                            + delivery.getDeliveryStreet() + "', '" + delivery.getDeliverySuburb() + "', '"
                            + delivery.getDeliveryState() + "', '" + delivery.getDeliveryPostal() + "', "
                            + ((double) Math.round(delivery.getDeliveryFee() * 100) / 100) + ", "
                            + (delivery.getDriverInstructions() == null ? "NULL "
                                    : ("'" + delivery.getDriverInstructions() + "' "))
                            + ");");
            return true;
        } catch (

        Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Exception is: " + e);
            return false;
        }
    }

    public boolean updateDelivery(Delivery delivery) {
        try {
            st.executeUpdate("UPDATE DELIVERY SET " +
                    "ORDER_ID = " + delivery.getOrderID() +
                    ", DRIVER_ID = " + delivery.getDriverID() +
                    ", DELIVERY_STREET = '" + delivery.getDeliveryStreet() +
                    "', DELIVERY_SUBURB = '" + delivery.getDeliverySuburb() +
                    "', DELIVERY_STATE = '" + delivery.getDeliveryState() +
                    "', DELIVERY_POSTAL = '" + delivery.getDeliveryPostal() +
                    "', DELIVERY_FEE = " + ((double) Math.round(delivery.getDeliveryFee() * 100) / 100) +
                    ", DRIVER_RATING = " + delivery.getDriverRating() +
                    ", DRIVER_INSTRUCTIONS = '" + delivery.getDriverInstructions() +
                    "', DRIVER_TIP = " + delivery.getDriverTip() +
                    " WHERE DELIVERY_ID = " + delivery.getDeliveryID());
            return true;
        } catch (

        Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Exception is: " + e);
            return false;
        }
    }

    public Delivery getDelivery(int orderID) {
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM DELIVERY WHERE ORDER_ID = " + orderID);
            while (rs.next()) {
                Delivery delivery = new Delivery(
                        rs.getInt("DELIVERY_ID"),
                        rs.getInt("ORDER_ID"),
                        rs.getInt("DRIVER_ID"),
                        rs.getString("DELIVERY_STREET"),
                        rs.getString("DELIVERY_SUBURB"),
                        rs.getString("DELIVERY_STATE"),
                        rs.getString("DELIVERY_POSTAL"),
                        rs.getFloat("DELIVERY_FEE"),
                        rs.getInt("DRIVER_RATING"),
                        rs.getString("DRIVER_INSTRUCTIONS"),
                        rs.getString("DRIVER_FEEDBACK"),
                        rs.getFloat("DRIVER_TIP"));
                return delivery;
            }
        } catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Exception is: " + e);
        }
        return null;
    }

    // Driver
    public DeliveryDriver getDriver(User user) {
        try {
            ResultSet rs = st.executeQuery("SELECT * " +
                    "FROM Driver INNER JOIN User ON Driver.User_ID = User.UserID " +
                    "WHERE Driver.User_ID = " + user.getUserID());
            while (rs.next()) {
                DeliveryDriver driver = new DeliveryDriver(
                        rs.getInt("User.UserID"),
                        rs.getString("User.First_Name"),
                        rs.getString("User.Last_Name"),
                        rs.getString("User.Password"),
                        rs.getString("User.Email"),
                        rs.getInt("User.PhoneNo"),
                        rs.getDate("User.DOB"),
                        rs.getInt("User.Street_Number"),
                        rs.getString("User.Street_Name"),
                        rs.getInt("User.Postcode"),
                        rs.getString("User.State"),
                        rs.getString("User.Suburb"),
                        rs.getString("User.Country"),
                        rs.getBoolean("User.Activated"),
                        rs.getInt("Driver.Driver_ID"),
                        rs.getString("NUMBER_PLATE"),
                        rs.getString("VEHICLE_DESCRIPTION"),
                        rs.getFloat("RATING"),
                        rs.getString("D_ACCOUNT_NAME"),
                        rs.getInt("D_BSB"),
                        rs.getInt("D_ACCOUNT_NUMBER"));
                return driver;
            }
        } catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Exception is: " + e);
        }
        return null;
    }

    // AppStaff Login - Benz
    public AppStaff appStaffLogin(String email, String pass) throws SQLException, Exception {
        System.out.println("Got sql");
        ResultSet rs = st
                .executeQuery("SELECT * FROM db.user U INNER JOIN db.appstaff A WHERE U.UserID = A.UserID AND " +
                        "Email ='" + email + "' AND Password='" + pass + "'");

        if (rs.next()) {
            String userID = rs.getString("U.UserID");
            String asID = rs.getString("A.A_Staff_ID");
            return new AppStaff(Integer.parseInt(userID), Integer.parseInt(asID));
        } else {
            return null;
        }
    }

    public ArrayList<Restaurant> fectRestaraunt() throws SQLException {
        String fetch = "SELECT * FROM RESTAURANT";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<Restaurant> temp = new ArrayList();

        while (rs.next()) {

            temp.add(new Restaurant(
                    rs.getString("Image_Reference"),
                    rs.getString("Restaraunt_Name"),
                    rs.getInt("Street_Number"),
                    rs.getString("Street_Name"),
                    rs.getInt("Postcode"),
                    rs.getString("State"),
                    rs.getString("Suburb"),
                    rs.getString("Country"),
                    rs.getBoolean("Activated"),
                    rs.getInt("ABN"),
                    rs.getString("Account_Name"),
                    rs.getInt("BSB"),
                    rs.getInt("Account_Number")));
        }
        return temp;
    }

    public ArrayList<MenuItem> fectMenuItem() throws SQLException {
        String fetch = "SELECT * FROM MENU_ITEM";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<MenuItem> temp = new ArrayList();

        while (rs.next()) {

            temp.add(new MenuItem(
                    rs.getInt("Item_ID"),
                    rs.getInt("Restaurant_ID"),
                    rs.getString("Item_Type"),
                    rs.getInt("Servings"),
                    rs.getFloat("Price"),
                    rs.getInt("Calories"),
                    rs.getString("Image"),
                    rs.getString("Description"),
                    rs.getString("Ingredients"),
                    rs.getString("Allergy"),
                    rs.getInt("Stock")));
        }
        return temp;

    }

    // public Order(int orderID, int customerID, String orderType, String status) {
    //     this.orderID = orderID;
    //     this.customerID = customerID;
    //     this.orderType = orderType;
    //     this.status = status;
    // }

    public Order createOrder(int customerID, int restaurantID, String orderType, String status) throws SQLException {
        String insert = "INSERT INTO db.order(Customer_ID, Restaurant_ID, Order_Type, Status)";
        String values = "VALUES (" + customerID + ", "+ restaurantID+", '" + orderType + "', '" + status + "')";
        try {
            st.executeUpdate(insert + values);
            //Select from order where its the last row? rs.get(orderID)
            //insert = "SELECT Order_ID FROM db.Order ORDER BY Order_ID DESC LIMIT 1";
            //int orderID = st.executeUpdate(insert);
            
            // String fetch = "SELECT Order_ID FROM db.Order ORDER BY Order_ID DESC LIMIT 1";
            // ResultSet rs = st.executeQuery(fetch);

            Order order = new Order(findOrderID(), customerID, orderType, status);

            //Order order = new Order(customerID, orderType, status);
            return order;
        } catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Exception is: " + e);
            return null;
        }

    }

    public int findOrderID(){
        //String fetch = "SELECT Order_ID FROM db.Order ORDER BY Order_ID DESC LIMIT 1";
        
        try {
            ResultSet rs = st.executeQuery("SELECT Order_ID FROM db.Order ORDER BY Order_ID DESC LIMIT 1");
            if (rs.next()) {
                return rs.getInt("Order_ID");
           } else {
                return 0;
           }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
        
    }

    public OrderItem createOrderItem(int orderID, int itemID, int quantity){
        String insert = "INSERT INTO order_item(Order_ID, Item_ID, Quantity)";
        String values = "VALUES (" + orderID + ", " + itemID + ", " + quantity + ")";
        try {
            st.executeUpdate(insert + values);
            OrderItem orderItem = new OrderItem(orderID, itemID, quantity);
            return orderItem;
        } catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Exception is: " + e);
            return null;
        }
    }

    public ArrayList<OrderItem> fectOrderItem(int orderID) throws SQLException {
        String fetch = "SELECT * FROM ORDER_ITEM WHERE Order_ID = '"+orderID+"'";
        ResultSet rs = st.executeQuery(fetch);
        ArrayList<OrderItem> temp = new ArrayList();

        while (rs.next()) {

            temp.add(new OrderItem(
                    rs.getInt("Order_ID"),
                    rs.getInt("Item_ID"),
                    rs.getInt("Quantity")));
        }
        return temp;

    }

    public void removeOrderItem(int orderID, int itemID){
        String insert = "DELETE FROM ORDER_ITEM WHERE Order_ID = '"+orderID+"' AND Item_ID= '"+itemID+"'" ;
        try {
            st.executeUpdate(insert);
        }
        catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void removeOrder(int orderID){
        String insert = " DELETE FROM order_item WHERE ORDER_ID = '"+orderID+"'";

        //String insert = "DELETE FROM ORDER WHERE Order_ID = '"+orderID+"' AND Item_ID= '"+itemID+"'" ;
        try {
            st.executeUpdate(insert);
        }
        catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
        }

        insert = "DELETE FROM DB.ORDER WHERE ORDER_ID = '"+orderID+"'";

        try {
            st.executeUpdate(insert);
        }
        catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
