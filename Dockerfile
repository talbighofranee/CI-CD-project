FROM openjdk:17
WORKDIR /app
COPY ../target/sprinprojet-2.0.0.jar  Foyer.jar
#EXPOSE 8085
EXPOSE 8086
CMD ["java", "-jar", "Foyer.jar"]





