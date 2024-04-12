FROM openjdk:17
EXPOSE 8086
WORKDIR /app
RUN apt-get update && apt-get install -y curl
RUN curl -o springprojet-2.0.0.jar -L "http://192.168.80.128:8081/repository/spring-releases/com/example/springprojet/2.0.0/springprojet-2.0.0.jar"

ADD target/sprinprojet-2.0.0.jar  Foyer.jar
ENTRYPOINT ["java","-jar","springprojet-2.0.0.jar"]