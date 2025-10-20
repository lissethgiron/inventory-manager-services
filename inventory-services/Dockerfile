FROM maven:3.9.6-eclipse-temurin-17
COPY /target/*.jar /usr/share/app.jar
ENTRYPOINT ["java", "-jar", "/usr/share/app.jar"]
EXPOSE 8080