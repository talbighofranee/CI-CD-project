FROM openjdk:17-alpine
EXPOSE 8086
WORKDIR /app
RUN apk update && apk add --no-cache curl
RUN curl -o Foyer.jar -L "http://192.168.80.128:8081/repository/spring-release/com/example/sprinprojet/2.0.0/sprinprojet-2.0.0.jar"
ENTRYPOINT ["java", "-jar", "Foyer.jar"]