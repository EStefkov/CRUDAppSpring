# CRUD User Management Application

This application provides a RESTful API for managing users with CRUD operations (Create, Read, Update, Delete).

## Prerequisites
Before running the application, ensure you have the following tools installed:

- Java Development Kit (JDK) - minimum JDK 8 or higher
- Apache Maven - for dependency management and project build tool
- Integrated Development Environment (IDE) - e.g., IntelliJ IDEA Ultimate

## Database Setup
For local development, you can use a MySQL database managed by XAMPP or UwAmp.

### Steps:
1. Install XAMPP (or UwAmp):

- Download and install XAMPP (or UwAmp) from their respective official websites.
- Start the XAMPP control panel and ensure both Apache and MySQL servers are running.
  
### Database Configuration:

- Access phpMyAdmin through your web browser (typically at http://localhost/phpmyadmin/ with XAMPP).
- Create a new database for your application (e.g., crud_app_db).

### Configure Application Properties:

- Navigate to application.properties file in your Spring Boot project (src/main/resources/application.properties).

- Configure the database connection properties:

- properties
Copy code
```
spring.datasource.url=jdbc:mysql://localhost:3306/crud_app_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
```
Replace crud_app_db with your database name.
Ensure spring.datasource.username and spring.datasource.password match your MySQL credentials.
Adjust spring.datasource.url if your MySQL server uses a different port or SSL configuration.

## Build and Run the Application

1. Import/Open Project:

- Open your IDE (IntelliJ IDEA Ultimate recommended) and import/open your Spring Boot project.
2. Build with Maven:

- Open a terminal or command prompt.

- Navigate to your project directory.

- Execute the following Maven command: ``` mvn clean install```
3. Run the Application:

- In your IDE, locate the main application class (CrudMavenAppApplication) and run it.

##  Accessing the Application
- Once the application is running, open a web browser and go to http://localhost:8080/ to access the application.
## Using the Application
- The application is now connected to your MySQL database.
- Perform CRUD operations on user entities through the provided API endpoints.

# API Documentation
### Base URL
Assume the base URL for the API endpoints is http://localhost:8080/users &  http://localhost:8080/api/users.

### Queries
1. Get All Users

- URL: /
- Method: GET
- Description: Retrieves all users.
- Parameters: sort (optional, default=firstName)
- Response: Returns a view (user-list.html) populated with a list of users.


2. Search Users
   
- URL: /?searchTerm={searchTerm}
- Method: GET
- Description: Searches users by first name containing the searchTerm.
- Parameters: searchTerm (optional)
- Response: Returns a view (user-list.html) filtered by the search term.

3. Get User by ID

- URL: /{id}
- Method: GET
- Description: Retrieves a specific user by their ID.
- Parameters: id (path variable)
- Response: 200 OK with JSON body containing the user details if found. 404 Not Found if user with the specified ID does not exist.

4. Create User

- URL: /
- Method: POST
- Description: Creates a new user.
- Request Body: Form data with user details (firstName, lastName, number, mailAddress, birthDate).
- Response: Redirects to the home page (/) with a success message flashed (User created successfully!).

5. Update User

- URL: /{id}
- Method: PUT
- Description: Updates an existing user.
- Parameters: id (path variable)
- Request Body: Form data with updated user details (firstName, lastName, number, mailAddress, birthDate).
- Response: Redirects to the users list page (/users) with a success message flashed (User updated successfully!). If user not found, redirects with an error message flashed (User not found!).

6. Edit User (Retrieve for Update)

- URL: /{id}/edit
- Method: GET
- Description: Retrieves a user by ID for editing purposes.
- Parameters: id (path variable)
- Response: Returns the view (update.html) populated with the user's details for editing. If user not found, redirects to the users list page (/users).

7. Delete User

- URL: /{id}
- Method: DELETE
- Description: Deletes a user by ID.
- Parameters: id (path variable)
- Response: Redirects to the users list page (/users) with a success message flashed (User deleted successfully!). If user not found, redirects with an error message flashed (User not found!).
