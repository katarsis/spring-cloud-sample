FROM openjdk:11-jre

ADD ./target/hystrix-dashboard.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/hystrix-dashboard.jar"]

EXPOSE 9000