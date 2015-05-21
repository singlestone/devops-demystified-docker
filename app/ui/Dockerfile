FROM ubuntu:latest
MAINTAINER Jay Hogan "jhogan@singlestoneconsulting.com"

# Change the date to invalidate the cache and force an apt update
ENV REFRESHED_AT 2015-05-20

# Install Pre-requisites
RUN apt-get -yq update && \
    apt-get -yq install --no-install-recommends curl python build-essential git ca-certificates rsync

# Install NodeJS
RUN mkdir /nodejs && curl http://nodejs.org/dist/v0.12.2/node-v0.12.2-linux-x64.tar.gz | tar xvzf - -C /nodejs --strip-components=1
ENV PATH $PATH:/nodejs/bin

# Create vagrant user and run all subsequent commands as vagrant
RUN adduser --disabled-password --gecos "" vagrant && \
  echo "vagrant ALL=(ALL) NOPASSWD:ALL" >> /etc/sudoers
USER vagrant
ENV HOME /home/vagrant

# Install npm bin stubs in HOME
RUN echo prefix = ~/.node >> ~/.npmrc
ENV PATH $HOME/.node/bin:$PATH

# Globally install useful npm modules
RUN npm install -g gulp && \ 
    npm install -g bower && \
	npm install -g yo && \
	npm install -g generator-gulp-angular

# gulp server = 3000, BrowserSync server = 3001
EXPOSE 3000 3001

# npm wants to chown all the things but NFS does not like that
# Do a little dance with symlinks, an npm wrapper script 
# and rsync to work around this issue
RUN mkdir /home/vagrant/bin
ADD npmwrapper.sh /home/vagrant/bin/npm
RUN sudo chmod a+x /home/vagrant/bin/npm
ENV PATH $HOME/bin:$PATH
RUN mkdir /home/vagrant/app
RUN ln -sf /app/package.json /home/vagrant/app/package.json

# Persist node_modules across container restarts
VOLUME /home/vagrant/app
