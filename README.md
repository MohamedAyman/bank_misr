# Project Name: Task Management

## Description

This project consists of 3 microservices:

- **[user-management]**: Responsible for authentication of users and creating a table of users & roles.
- **[task-management]**: Core application, responsible for CRUD operations for task management.
- **[notification]**: Responsible for sending mail notifications.

## Technologies

- **Programming Languages**: Java
- **Frameworks/Libraries**: Spring Boot, Spring Security, JPA, ...
- **Databases**: MySQL, H2
- **Containerization**: Docker

## Prerequisites

- **Docker**: Installed and running on your machine.
- **Docker Compose**: Installed and running on your machine (optional, but recommended).

## How to run services

from Application root folder open terminal and run command
`
./run_services.sh
`

this will build the 3 services and run docker compose

## Test endpoints
using [postman collection](bank_misr.postman_collection.json)
