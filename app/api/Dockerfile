FROM ubuntu:latest
MAINTAINER Jay Hogan "jhogan@singlestoneconsulting.com"

# Change the date to invalidate the cache and force an apt update
ENV REFRESHED_AT 2015-05-20

# Install Pre-requisites
RUN apt-get -yq update && \
	apt-get -yq install --no-install-recommends openjdk-7-jdk curl build-essential git ca-certificates

# Install Maven
ENV MAVEN_VERSION 3.3.3
RUN curl -fsSL http://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar xzf - -C /usr/share \
  && mv /usr/share/apache-maven-$MAVEN_VERSION /usr/share/maven \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
ENV MAVEN_HOME /usr/share/maven

# Create vagrant user and run all subsequent commands as vagrant
RUN adduser --disabled-password --gecos "" vagrant && \
  echo "vagrant ALL=(ALL) NOPASSWD:ALL" >> /etc/sudoers
USER vagrant
ENV HOME /home/vagrant

# Persist maven repository across container restarts
VOLUME /home/vagrant/.mvn
