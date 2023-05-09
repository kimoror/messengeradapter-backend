FROM openjdk:17

EXPOSE 8080

WORKDIR /applications

COPY target/backend.jar /applications/backend.jar

ENTRYPOINT ["java","-jar", "backend.jar"]