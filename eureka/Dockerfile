FROM openjdk:11-jre

ADD ./target/eureka-server.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/eureka-server.jar"]

EXPOSE 8761