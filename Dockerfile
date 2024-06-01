FROM openjdk:17-alpine
ADD target/fulbito-application.jar fulbito-application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "fulbito-application.jar"]
