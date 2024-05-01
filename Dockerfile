FROM ubuntu:latest AS build
RUN apt-get update
LABEL authors="sameer"

COPY target/*.jar personalizeddata.jar
EXPOSE 8080
CMD [ "java", "-jar","personalizeddata.jar" ]