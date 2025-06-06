FROM openjdk:21
WORKDIR /app

COPY target/crud-0.0.1-SNAPSHOT.jar /app/crud-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar" , "crud-0.0.1-SNAPSHOT.jar"]