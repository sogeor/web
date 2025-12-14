FROM eclipse-temurin:25-jre-alpine

WORKDIR /run
COPY build/libs/web-*.jar web.jar

EXPOSE 8080
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "web.jar"]
