FROM openjdk:11-jre

ADD ./target/config-server.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/config-server.jar"]

HEALTHCHECK --interval=10s --timeout=3s CMD curl -f http://localhost:8888/actuator/health || exit 1

EXPOSE 8888