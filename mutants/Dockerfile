FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 9081
COPY build/libs/mutants-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]