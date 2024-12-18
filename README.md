
# Task Management Microservices

This project implements a task management system consisting of three independent microservices:

- **User Management**: Handles user authentication and role-based access control.
- **Task Management**: Manages task creation, updates, retrieval, and deletion.
- **Notification**: Sends email notifications based on task updates.

## Technologies

- **Programming Language**: Java
- **Frameworks & Libraries**: Spring Boot, Spring Security, Spring Data JPA
- **Databases**: MySQL (Primary), H2 (For testing)
- **Containerization**: Docker

## Prerequisites

Before running the application, ensure you have the following installed on your machine:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Running the Services

To start all services locally, follow these steps:

1. Open a terminal in the root directory of the project.
2. Run the following command to build and start the services using Docker Compose:

   ```bash
   ./run_services.sh
   ```

   This script will:
    - Build the Docker images for the three microservices.
    - Start the services using Docker Compose.

## API Endpoints

### Authentication

#### User Login
```bash
curl --location 'localhost:8080/oauth/login' --header 'Content-Type: application/json' --header 'Cookie: JSESSIONID=8A7FC5CA39F46B2BFB181E1F25F2C90D' --data '{
  "username": "user",
  "password": "123456"
}'
```

#### Admin Login
```bash
curl --location 'localhost:8080/oauth/login' --header 'Content-Type: application/json' --header 'Cookie: JSESSIONID=8A7FC5CA39F46B2BFB181E1F25F2C90D' --data '{
  "username": "admin",
  "password": "123456"
}'
```

### Task Management

#### Admin Create Task
```bash
curl --location 'localhost:8081/task' --header 'Content-Type: application/json' --header 'Authorization: Bearer {ADMIN_JWT}' --header 'Cookie: JSESSIONID=8A7FC5CA39F46B2BFB181E1F25F2C90D' --data '{
  "title": "Task 99",
  "description": "test555",
  "status": { "id": 1, "statusName": "TODO" },
  "user": { "id": 2 },
  "priorityTask": 0,
  "dueDate": "2024-12-27T10:14:19"
}'
```

#### User Get All Tasks
```bash
curl --location 'localhost:8081/tasks' --header 'Authorization: Bearer {USER_JWT}' --header 'Cookie: JSESSIONID=8A7FC5CA39F46B2BFB181E1F25F2C90D'
```

#### User Filter Tasks
```bash
curl --location 'localhost:8081/tasks?title=Task%209' --header 'Authorization: Bearer {USER_JWT}' --header 'Cookie: JSESSIONID=8A7FC5CA39F46B2BFB181E1F25F2C90D'
```

#### User Get Single Task
```bash
curl --location 'localhost:8081/task/5' --header 'Authorization: Bearer {USER_JWT}' --header 'Cookie: JSESSIONID=8A7FC5CA39F46B2BFB181E1F25F2C90D'
```

#### User Delete Task
```bash
curl --location --request DELETE 'localhost:8081/task/4' --header 'Authorization: Bearer {USER_JWT}' --header 'Cookie: JSESSIONID=8A7FC5CA39F46B2BFB181E1F25F2C90D'
```

#### User Update Task
```bash
curl --location --request PUT 'localhost:8081/task' --header 'Authorization: Bearer {USER_JWT}' --header 'Content-Type: application/json' --header 'Cookie: JSESSIONID=8A7FC5CA39F46B2BFB181E1F25F2C90D' --data '{
  "id": 5,
  "title": "Task 7",
  "description": "Task 7 description",
  "status": { "id": 1, "statusName": "TODO" },
  "user": { "id": 2 },
  "priorityTask": 0,
  "dueDate": "2024-12-19T10:14:19"
}'
```

### Notification Service

#### Send Task Notification Email
```bash
curl --location 'localhost:8082/send' --header 'Content-Type: application/json' --header 'Cookie: JSESSIONID=8A7FC5CA39F46B2BFB181E1F25F2C90D' --data-raw '{
  "to": "test@gmail.com",
  "title": "Task 1",
  "description": "Task updated",
  "status": "on_going",
  "dueDate": "2024-12-17T10:14:19"
}'
```

## Postman Collection

For convenience, a Postman collection is included to test all endpoints.

- [Download Postman Collection](bank_misr.postman_collection.json)
