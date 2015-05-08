FROM ubuntu:latest
RUN apt-get update
RUN apt-get -y install openjdk-7-jre
RUN ln -s /build/dist/api /app
EXPOSE 8080
WORKDIR /app
CMD java -jar api.jar
