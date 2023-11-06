FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /bank
COPY . /bank/.
RUN mvn -f /bank/pom.xml clean package -Dmaven.test.skip=true

FROM eclipse-temurin:latest
WORKDIR /bank
COPY --from=builder /bank/target/*.jar /bank/*.jar
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "/bank/*.jar"]