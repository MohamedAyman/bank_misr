FROM openjdk:17
WORKDIR /app
COPY target/notification-0.0.1-SNAPSHOT.jar notification.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "notification.jar"]
