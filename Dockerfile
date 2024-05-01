FROM openjdk:8-jre-alpine

WORKDIR /app

COPY target/personalizeddata-0.0.1-SNAPSHOT.jar /app/personalizeddata.jar
EXPOSE 8080

CMD ["java", "-jar", "personalizeddata.jar"]