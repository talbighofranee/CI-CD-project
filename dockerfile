FROM openjdk:17
EXPOSE 8086
ADD target/sprinprojet-2.0.0.jar  Foyer.jar
ENTRYPOINT ["java","-jar","Foyer.jar"]