FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
COPY src ./src
RUN chmod +x mvnw
RUN ./mvnw package -DskipTests
RUN ls -l ./target
CMD ["java", "-jar", "target/sistemaweb-0.0.1-SNAPSHOT.war"]
