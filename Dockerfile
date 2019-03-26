FROM openjdk:8-jre-alpine
VOLUME /tmp
ADD /target/Gestion-catalogue.jar Gestion-catalogue.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Gestion-catalogue.jar"]