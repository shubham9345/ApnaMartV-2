# ApnaMartV-2 🛒
ApnaMart is a premier online grocery application dedicated to transforming your everyday shopping experience. With a comprehensive range of high-quality groceries, household essentials, and fresh produce, we bring the convenience of modern technology directly to your doorstep. Our user-friendly app simplifies meal planning and daily routines by offering an extensive catalog of products sourced from trusted brands, all at competitive prices.


# Key Features🚀
1. The backend is developed using the Spring and Spring Boot frameworks and relies on a MySQL database for robust data management.
2. To ensure secure user sessions, JWT (JSON Web Token) authentication is employed, while Spring Security safeguards sensitive user data and protects the system from cyber threats.
3. For efficient database operations, Hibernate is utilized to perform CRUD functions and HQL (Hibernate Query Language) is leveraged for complex queries.
4. Adopted custom exception handling with a centralized global error handler to deliver precise, context-aware, and maintainable error responses.

# Tech Stack 
 Java, Adanced java, Oops, Spring, Springboot, Spring Security, JWT authentication, microservices, Hibernate,Hql, MySQL, REST API, Exception Handling

 # System Design of Application 📌
 Layered Architecture: </br>
 <ul> <li> Presentation Layer (Controller):</li> 
This layer handles HTTP requests, maps them to appropriate service calls, and then converts service responses into RESTful responses (often in JSON). Controllers act as the entry point for client interactions.
<li>Business Logic Layer (Service): </li>
The Service layer contains the core business logic. It performs operations such as data validation, business rule enforcement, and orchestration of calls to lower layers. This level ensures that your use-case scenarios are implemented consistently.
   <li> Data Access Layer (Repository): </li>
The Repository layer provides an abstraction for data persistence. Using Spring Data repositories (or similar), your code interacts with relational or NoSQL databases without having to write boilerplate SQL code.
   <li>Domain Model (Entity):</li>
Entities are plain old Java objects (POJOs) that map to database tables (or collections). They represent the state and data of your application, and their structure typically reflects the underlying database schema.
  <li>Utility/Helper Layer: </li> 
This layer provides common functions, such as logging, date formatting, configuration handling, exception handling, or third-party API integrations, which can be utilized across the entire application.
  <li> Security: </li>
Integrate Spring Security or OAuth2 for authenticating and authorizing API requests. Security configurations can be placed in a separate config package.
</ul>
