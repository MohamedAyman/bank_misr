FROM openjdk:17
WORKDIR /app
COPY user-management/target/user.management-0.0.1.jar user-management.jar
COPY task-management/target/task.management-0.0.1-SNAPSHOT.jar task-management.jar
COPY notification/target/notification-0.0.1-SNAPSHOT.jar notification.jar

ENTRYPOINT java -jar /app/user-management.jar & java -jar /app/task-management.jar & /app/notification.jar & wait
