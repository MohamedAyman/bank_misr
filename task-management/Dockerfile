FROM openjdk:17
WORKDIR /app
COPY target/task.management-0.0.1-SNAPSHOT.jar task_management.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "task_management.jar"]
