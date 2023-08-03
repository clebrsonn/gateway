FROM openjdk:20-jdk-slim
EXPOSE $PORT
VOLUME /tmp
COPY build/libs/gateway-*.jar app.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom $JAVA_OPTS -Dserver.port=$PORT", "-jar", "/app.jar"]