FROM eclipse-temurin:25-jre-alpine

WORKDIR /run

RUN apk update && \
    apk add --no-cache gcompat gcc

COPY build/libs/web-*.jar web.jar

EXPOSE 8080
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "web.jar"]
