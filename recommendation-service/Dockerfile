FROM openjdk:11-jre

ADD ./target/recommendation-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/recommendation-service.jar"]


EXPOSE 8080