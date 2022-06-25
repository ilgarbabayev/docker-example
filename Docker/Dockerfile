FROM openjdk:11
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
COPY feedback ./feedback
EXPOSE 8080
CMD ["./mvnw", "spring-boot:run"]