FROM ubuntu:latest
MAINTAINER Jay Hogan "jhogan@singlestoneconsulting.com"

# Change the date to invalidate the cache and force an apt update
ENV REFRESHED_AT 2015-05-20

# Update packages and install jdk
RUN apt-get -yq update && \
    apt-get -yq install openjdk-7-jre

RUN ln -s /build/dist/api /app

EXPOSE 8080

WORKDIR /app

CMD java -jar api.jar
