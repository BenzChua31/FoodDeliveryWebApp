<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Welcome to the Register Page</title>
    </head>
    <body>
        <h1>Register</h1>
        <br/>
        <form action="CustomerRegisterServlet" method="post">
            <table id="registerTable">
                <tr>
                    <td><label for="firstName">First Name:</label></td>
                    <td><input type="text" name="firstName" required="true"></td>
                </tr> 
                <tr>
                    <td><label for="lastName">Last Name:</label></td>
                    <td><input type="text" name="lastName" required="true"></td>
                </tr> 
                <tr>
                    <td><label for="email">Email:</label>
                    <td><input type="text" name="email" required="true"></td>
                </tr>                
                <tr>
                    <td><label for="password">Password:</label>
                    <td><input type="password"  name="password" required="true"></td>
                </tr>
                <tr>
                    <td><label for="address">Address:</label>
                    <td><input type="text"  name="address" required="true"></td>
                </tr>
                <tr>
                    <td><label for="phone">Phone:</label>
                    <td><input type="text"  name="phone" required="true"></td>
                </tr>
                <tr>
                    <td><label for="dateOfBirth">Date of Birth</label>
                    <td><input type="date" name="dob" required="true"></td>
                </tr>
                <tr>
                    <td><label for="cardNumber">Card Number</label>
                    <td><input type="text" name="cardNumber" required="true"></td>
                </tr> 
                <tr>
                    <td><label>Card Expiration</label>
                    <td><input type="text" name="cardExpiration" required="true"></td>
                </tr> 
                <tr>
                    <td><label>Card Pin</label>
                    <td><input type="text" name="cardPin" required="true"></td>
                </tr> 
                <tr>
                    <td><label>Card Name</label>
                    <td><input type="text" name="cardName" required="true"></td>
                </tr> 

            </table> 
            <div class="center">
                <input type ="submit" value="Sign Up">
            </div>    
        </form>
        <a href="./index.jsp">Go to Index Page</a>
    </body>
</html>