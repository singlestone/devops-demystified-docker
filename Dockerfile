FROM ubuntu:latest

# Install Pre-requisites
RUN apt-get -y update
RUN apt-get install --no-install-recommends -y -q openjdk-7-jdk curl python build-essential git ca-certificates

# Install Maven
ENV MAVEN_VERSION 3.3.3
RUN curl -fsSL http://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar xzf - -C /usr/share \
  && mv /usr/share/apache-maven-$MAVEN_VERSION /usr/share/maven \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
ENV MAVEN_HOME /usr/share/maven

# Install NodeJS
RUN mkdir /nodejs && curl http://nodejs.org/dist/v0.12.2/node-v0.12.2-linux-x64.tar.gz | tar xvzf - -C /nodejs --strip-components=1
ENV PATH $PATH:/nodejs/bin
RUN npm install gulp -g
RUN npm install bower -g

# TODO - Everything above this line should be a base image

# Build the API
WORKDIR /build/api
ADD api /build/api
RUN mvn clean package
RUN mkdir /build/dist
RUN mkdir /build/dist/api
RUN cp target/api.jar /build/dist/api
RUN cp application.properties /build/dist/api


# Build the UI
WORKDIR /build/ui
ADD ui/package.json /build/ui/
RUN npm install
ADD ui/bower.json /build/ui/
RUN bower --allow-root install
ADD ui /build/ui
RUN gulp build
RUN mkdir /build/dist/ui
RUN cp -a dist/. /build/dist/ui/


VOLUME /build/dist
