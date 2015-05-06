FROM ubuntu:latest
RUN apt-get update
RUN apt-get -y install openjdk-7-jre
RUN mkdir /app
COPY ./target/api.jar /app/
COPY ./application.properties /app/
EXPOSE 8080
WORKDIR /app
CMD java -jar api.jar
