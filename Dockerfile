FROM ubuntu:latest AS build
RUN apt-get update
LABEL authors="sameer"

ENTRYPOINT ["top", "-b"]