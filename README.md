# 📝 Blog App APIs (Spring Boot)

A fully-featured **RESTful Blog Application** built with **Spring Boot**, **Spring Security (JWT)**, **JPA/Hibernate**, and **MySQL**.  
It supports **user authentication, role-based access control, CRUD APIs for posts, comments, categories, file uploads, and search functionality**.  
Includes **Swagger API documentation** for easy testing. 🚀

---

## 🔥 Features
✅ User Registration & Login (JWT Authentication)  
✅ Role-Based Authorization (Admin & User roles)  
✅ CRUD Operations for Posts, Categories, and Comments  
✅ Search Posts by Title Keyword  
✅ Pagination & Sorting for Posts  
✅ File Uploads for Post Images  
✅ Global Exception Handling  
✅ Swagger UI for API Testing  
✅ Secure Passwords using BCrypt  

---

## 🛠️ Tech Stack
| Technology      | Purpose                          |
|-----------------|----------------------------------|
| Spring Boot     | Application framework           |
| Spring Security | Authentication & Authorization  |
| JWT             | Token-based authentication     |
| Spring Data JPA | ORM for DB operations          |
| Hibernate       | JPA Implementation             |
| MySQL           | Database                       |
| Maven           | Build Tool                     |
| Swagger (OpenAPI)| API Documentation             |
| Lombok          | Boilerplate code reduction     |

---

## ⚙️ Project Structure
src/main/java/com/nagesh/apis/
│
├── config/ # Security & Swagger configuration
├── entities/ # JPA Entities (User, Post, Comment, Category, Roles)
├── exceptions/ # Global exception handling
├── jwt/service/ # JWT Token utilities & filter
├── payloads/ # DTOs (Data Transfer Objects)
├── repositories/ # Spring Data JPA Repositories
├── service/ # Service Interfaces
├── service/impl/ # Service Implementations
└── controllers/ # REST API Controllers


---

## 🚀 Getting Started

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/Bhatnagesh/blog-app-apis.git
cd blog-app-apis

2️⃣ Configure Database

Create a database in MySQL:

CREATE DATABASE blog_app;


Update your application.properties (or application.yml):

3️⃣ Run the Application
mvn spring-boot:run
or run the main class:
BlogAppApisApplication.java

4️⃣ Access API Docs

Once the app is running, open:
🔗 http://localhost:8080/swagger-ui.html

🔑 API Endpoints (Quick Reference)
HTTP Method	Endpoint	Description
POST	/api/user/	Register a new user
POST	/api/user/login	Login & get JWT token
GET	/api/post/	Get all posts (Paginated)
POST	/api/user/{id}/post	Create post for a user
GET	/api/post/search/{query}	Search posts by title
GET	/api/category/	Get all categories
POST	/api/category/	Create category
DELETE	/api/post/{id}	Delete a post

🔹 Note: All endpoints (except registration/login) are JWT-protected.

🔒 Security

JWT Token required for most endpoints.

Use Authorization: Bearer <token> in API requests after login.

📸 Swagger Screenshot

👨‍💻 Author

👤 Nagesh Bhat
