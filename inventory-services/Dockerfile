FROM maven:3.9.6-eclipse-temurin-17
WORKDIR /app
COPY target/*.jar /usr/share/app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/usr/share/app.jar"]