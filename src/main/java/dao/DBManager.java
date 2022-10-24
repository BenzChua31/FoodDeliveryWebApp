package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.*;

public class DBManager {

    private Connection conn;

    public DBManager(Connection conn) throws SQLException {
        this.conn = conn;
    }

    public void testAdder(String firstName, String lastName) {
        String fetch = "INSERT INTO db.tables.Customer " + "VALUES (" + firstName + ", '" + lastName + "')";
        System.out.println(fetch);
    }

    public void addCustomer(String firstName, String lastName, String password, String email, String phoneNumber,
            String dob, String streetNumber, String streetName, String postcode, String state, String suburb,
            String country, boolean activated, String cardNumber, String cardExpiration, int cardPin, String cardName)
            throws SQLException {
        String fetch = "INSERT INTO db.tables.Customer " + "VALUES (" + firstName + ", '" + lastName + "', '" + password
                + "', '" + email + "', '" + phoneNumber + "', '" + dob + "', '" + streetNumber + "', '" + streetName
                + "', '" + postcode + "', '" + state + "', '" + suburb + "', '" + country + "', '" + activated + "', '"
                + cardNumber + "', '" + cardExpiration + "', '" + cardPin + "', '" + cardName + "')";
        System.out.println(fetch);
    }

    // Staff
    public void addStaff(String firstName, String lastName, String password, String email, String phoneNumber,
            String dob, String streetNumber, String streetName, String postcode, String state, String suburb,
            String country, boolean activated, int privilege, String position) throws SQLException {
        String fetch = "INSERT INTO db.tables.Staff " + "VALUES (" + firstName + ", '" + lastName + "', '" + password
                + "', '" + email + "', '" + phoneNumber + "', '" + dob + "', '" + streetNumber + "', '" + streetName
                + "', '" + postcode + "', '" + state + "', '" + suburb + "', '" + country + "', '" + activated + "', '"
                + privilege + "', '" + position + "')";
        System.out.println(fetch);
    }

    public Customer findCustomer(String email, String password) {
        return null;
    }

    public Staff findStaff(String email, String password) {
        return null;
    }

    public boolean validStaffRes(int staffID, int restaurantID) {
        try {
            Statement st = conn.createStatement();
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
            Statement st = conn.createStatement();
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
            Statement st = conn.createStatement();
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
            Statement st = conn.createStatement();
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
            Statement st = conn.createStatement();
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
            Statement st = conn.createStatement();
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
            Statement st = conn.createStatement();
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
            Statement st = conn.createStatement();
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
            Statement st = conn.createStatement();
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
            Statement st = conn.createStatement();
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
            Statement st = conn.createStatement();
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
        Statement st = conn.createStatement();
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
        Statement st = conn.createStatement();
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

    public ArrayList<MenuItem> fectMenuItem(int restaurantID) throws SQLException {
        String fetch = "SELECT * FROM MENU_ITEM WHERE RESTAURANT_ID = '" + restaurantID + "' ";
        Statement st = conn.createStatement();
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

    public Order createOrder(int customerID, int restaurantID, String orderType, String status) throws SQLException {
        String insert = "INSERT INTO db.order(Customer_ID, Restaurant_ID, Order_Type, Status)";
        String values = "VALUES (" + customerID + ", " + restaurantID + ", '" + orderType + "', '" + status + "')";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(insert + values);
            // Select from order where its the last row? rs.get(orderID)
            // insert = "SELECT Order_ID FROM db.Order ORDER BY Order_ID DESC LIMIT 1";
            // int orderID = st.executeUpdate(insert);

            // String fetch = "SELECT Order_ID FROM db.Order ORDER BY Order_ID DESC LIMIT
            // 1";
            // ResultSet rs = st.executeQuery(fetch);

            Order order = new Order(findOrderID(), customerID, orderType, status);

            // Order order = new Order(customerID, orderType, status);
            return order;
        } catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Exception is: " + e);
            return null;
        }

    }

    public int findOrderID() {
        // String fetch = "SELECT Order_ID FROM db.Order ORDER BY Order_ID DESC LIMIT
        // 1";

        try {
            Statement st = conn.createStatement();
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

    public Customer findCustomer(int customerID) {
        try {
            Statement st = conn.createStatement();
            String fetch = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID = " + customerID;
            ResultSet rs = st.executeQuery(fetch);

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("Customer_ID"),
                        rs.getLong("Card_Number"),
                        rs.getDate("Card_Expiration"),
                        rs.getInt("Card_Pin"),
                        rs.getString("Card_Name"));
                return customer;
            }
        }

        catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Exception is: " + e);
        }
        return null;

    }

    public OrderItem createOrderItem(int orderID, int itemID, int quantity, String comment) {

        try {
            Statement st = conn.createStatement();
            Statement st1 = conn.createStatement();
            // String fetch = "SELECT * FROM ORDER_ITEM";
            // ResultSet rs = st.executeQuery(fetch);
            // while (rs.next()) {
            //     if (rs.getInt("Item_ID") == itemID && rs.getInt("Order_ID") == orderID) {
            //         int add = rs.getInt("Quantity") + 1;
            //         String insert = "UPDATE order_item SET Quantity = " + add + " WHERE Item_ID = " + itemID + " AND ORDER_ID = "+orderID+" ";
            //         st1.executeUpdate(insert);
            //         return null;
            //     }

            // }
            String insert = "INSERT INTO order_item(Order_ID, Item_ID, Quantity, Comment)";
            String values = "VALUES (" + orderID + ", " + itemID + ", " + quantity + ", '" + (comment != null ? comment : "") + "')";
            try {
                st.executeUpdate(insert + values);
                OrderItem orderItem = new OrderItem(orderID, itemID, quantity, comment);
                return orderItem;
            } catch (Exception e) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
                System.out.println("Exception is: " + e);
                return null;
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return null;
    }

    public ArrayList<OrderItem> fectOrderItem(int orderID) throws SQLException {
        String fetch = "SELECT * FROM ORDER_ITEM WHERE Order_ID = '" + orderID + "'";
        Statement st = conn.createStatement();
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

    public void removeOrderItem(int orderID, int itemID) {
        String insert = "DELETE FROM ORDER_ITEM WHERE Order_ID = '" + orderID + "' AND Item_ID= '" + itemID + "'";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(insert);
        } catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void removeOrder(int orderID) {
        String insert = " DELETE FROM order_item WHERE ORDER_ID = '" + orderID + "'";

        // String insert = "DELETE FROM ORDER WHERE Order_ID = '"+orderID+"' AND
        // Item_ID= '"+itemID+"'" ;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(insert);
        } catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
        }

        insert = "DELETE FROM DB.ORDER WHERE ORDER_ID = '" + orderID + "'";

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(insert);
        } catch (Exception e) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
