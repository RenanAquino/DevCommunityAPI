# DevCommunityAPI

Welcome to the DevCommunity API, an interactive forum for developers. This API, built with Java and Spring Boot, provides a secure platform for authentication, creation of posts and comments, and uses MongoDB as its database. Ideal for knowledge exchange and discussions among developers.

## Routes

The API offers the following main routes:

- **Authentication:**
  - `POST /auth/login`: Performs user login and returns a JWT token.
  - `POST /auth/register`: Registers new users.

- **Users:**
  - `PUT /users/update/{id}`: Updates a user by id.
  - `DELETE /users/delete/{id}`: Deletes a user by id.

- **Admin:**
  - `GET /admin`: Retrieves all users.
  - `GET /admin/{id}`: Retrieves a user by id.

- **Posts:**
  - `GET /posts`: Retrieves all posts.
  - `GET /posts/{postId}`: Retrieves a specific post.
  - `POST /posts/create`: Creates a new post.
  - `PUT /posts/update/{postId}`: Updates an existing post.
  - `DELETE /posts/delete/{postId}`: Deletes a post.

- **Comments:**
  - `GET /comments`: Retrieves all comments.
  - `GET /comments/{id}`: Retrieves a comment by id.
  - `GET /comments/post/{postId}`: Retrieves all comments from a post.
  - `POST /posts/{postId}/comments`: Adds a new comment to a post.
  - `PUT /comments/update/{id}`: Updates an existing comment.
  - `DELETE /comments/delete/{commentId}`: Deletes a comment.

## Technologies Used

- Java
- Spring Boot
- Spring Security
- MongoDB
- JSON Web Token (JWT)

## How to Start

1. **Prerequisites:**
   - Java 11 or higher installed.
   - MongoDB installed and running.

2. **Database Configuration:**
   - Configure MongoDB properties in the `application.properties` file.

3. **Project Execution:**
   - Run the Spring Boot application.

4. **API Access:**
   - Access the API at `http://localhost:8080`.

## Contribution

Contributions are welcome! If you find bugs or want to add new features, feel free to open an issue or submit a pull request.

## Contact

For questions or more information, contact me at renanaqpython@gmail.com.

---
