FROM openjdk:11-jre

ADD ./target/recommendation-client.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/recommendation-client.jar"]


EXPOSE 8081