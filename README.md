# Food-Delivery-System-AdvSoftDev-
UTS - Advanced Software Development Group Project 

Food Delivery Web Application that provides an online medium for customers
to order from restaurants and restaurant owners to sell food

This project primarily focuses on the use of Azure DevOps

**_Tools Used:_**
Azure DevOps, Azure, HTML, CSS, JS, Bootstrap, Java, MySQL, IntelliJ

- Completed using Java Server Pages (JSP), Java Servlets on a Tomcat server
- Followed the MVC architecture
- Achieved CI/CD by utilizing Kanban Board, and Azure Pipelines
- Utilized Azure Database for MySQL to enable cloud data storage, which can be accessed through MySQL Workbench
- Deployed using Azure App Service
- Incorporated a responsive and minimalistic design using CSS, JS and Bootstrap

------------------------------------------------------------------

**_Features:_**

**_Benz:_** 
- Restaurant Category Management system 
Category CRUD operations, Multiple category to restaurants assignments

- Restaurant Management system
Restaurant CRUD operations, Restaurant activation / deactivation

- Corporate Staff system
Toggle-able management mode

- App Staff Access system
Corporate staff login and logout

Other Amazing Features by My Team Members:  
- Delivery system
- Review system Coupon Management system
- Restaurant Employee Management system
- Menu Management system & Menu Access system         
- User Access system & User Management system
- Ordering system & Payment system

------------------------------------------------------------------
**Source Code for my Features**

azure-pipelines.yml - CI/CD pipeline  
src/main/java/controller/rms - All restaurant and restaurant category related servlets  
src/main/java/controller/AppStaffLoginServlet - Corporate staff login  
src/main/java/controller/IndexServlet - Home page   
src/main/java/controller/ConnServlet - Database connection initializer 
src/main/java/controller/Validator - Input Validator for Controllers   
src/main/java/dao/* - MySQL database connection initializer, SQL statements  
db - For database and table population and deletion  
pom.xml - For all dependencies  
src/main/webapp/   
      - setResCat - Category assignment JSP  
      - modifyRes, modifyCat - Restaurant / Category add/update JSP  
      - manageRes, manageCat - Restaurant / Category list JSP  
      - index - Home page JSP  
      - appStaffLogin - Corporate Staff Login JSP  
      - css - CSS for relevant JSPs _(They have the same name)_  

------------------------------------------------------------------
