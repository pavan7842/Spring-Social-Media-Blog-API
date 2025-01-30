# 📌 Spring Social Media Blog API

## **Overview**
Spring Social Media Blog API is a **RESTful backend** built using **Spring Boot**. It enables user management, message creation, and interaction in a micro-blogging environment.

This API provides endpoints for:
- **User Registration & Login**
- **Message Posting, Updating, and Deleting**
- **Fetching Messages for Users**

## **🔧 Tech Stack**
- **Backend:** Java, Spring Boot, Spring Data JPA, Spring Web
- **Database:** H2 / MySQL (Configurable)
- **Tools:** Postman (API Testing), Maven (Dependency Management)

## **🚀 Features**
- **Spring Boot API** for user authentication and message management.
- **RESTful endpoints** to perform CRUD operations.
- **Spring Data JPA** for seamless database interactions.
- **Exception Handling** for better error management.

## **🔗 Endpoints**
| Method | Endpoint | Description |
|--------|---------|-------------|
| `POST` | `/register` | Register a new user |
| `POST` | `/login` | Authenticate user login |
| `POST` | `/messages` | Create a new message |
| `GET` | `/messages` | Fetch all messages |
| `GET` | `/messages/{id}` | Retrieve message by ID |
| `PATCH` | `/messages/{id}` | Update message text |
| `DELETE` | `/messages/{id}` | Delete a message |

## **📖 How to Run**
1. **Clone the repository:**
   ```sh
   git clone https://github.com/pavan7842/pavan7842-pep-spring-project.git
   cd spring-social-media-blog-api

2. **Run the application:**
mvn spring-boot:run

3. **Test APIs using Postman or Curl:**

**Documentation**

For detailed project requirements and user stories, see PROJECT_DETAILS.md.