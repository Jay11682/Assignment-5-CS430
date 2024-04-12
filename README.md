# Assignment-5-CS430
Assignment #5: Application Development and SQL

### Task Description:
You are tasked with developing a web-based user device management system using Java
servlets for the backend and JavaScript and HTML for the front end. The following system
will perform the following tasks:
1. Search for existing users in the database (by name or ID).
2. Create a new user in the database.
3. Update the information of current users based on entered UserID.
4. Delete users based on their UserID.
5. Update the “uses” table by adding a uses record for some user who has used a
particular device on specified date and for a given duration. (For example, Ellisa
Brooks who is a regular user, has used tablet (iPad 9th Gen) on 04/09/2024 for 2
hours)
6. In addition to the basic search, lookup for a user (by Id) and display devices used
and usage duration within specified date range (joining three tables).

### Tasks to be Performed:
Task 1-Database Setup (Already done: Use the database from Assignment#04):
1. Design a relational database schema to store Users, Uses and DeviceType.
2. Set up a MySQL database and create tables according to the schema.


Task 2-Backend Development (Java Servlets):
Implement servlets to handle back-end tasks.
1. Use JDBC Connect servlets to the MySQL database to perform database
operations.
2. Use JDBC functions to run the requested queries
3. Write functions to provide API interfaces to respond to the client requests
4. Run the servlet on some local web server (e.g. Apache Tomcat, Glassfish, or JBoss,
etc.)


Task 3-Frontend Development (JavaScript):
Develop a front-end interface using HTML and CSS for user interaction with the web server.
1. Implement JavaScript functions to send AJAX requests to servlets.
2. Forms to enter the data used by the requests
3. Display request results, like user addition status, dynamically on the webpage.
