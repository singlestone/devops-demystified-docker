FROM ubuntu:latest
MAINTAINER Jay Hogan "jhogan@singlestoneconsulting.com"

# Change the date to invalidate the cache and force an apt update
ENV REFRESHED_AT 2015-05-20

# Update packages and install apache2
RUN apt-get -yq update && \
    apt-get -yq install apache2

# Enable necessary apache modules
RUN a2enmod userdir && \
	a2enmod headers && \
 	a2enmod proxy && \
	a2enmod proxy_http

RUN ln -s /build/dist/ui /app

# Copy apache VirtualHost configuration
COPY ./apache.conf /etc/apache2/sites-available/devops-demystified.conf

# Disable default sites and enable new site
RUN a2dissite 000-default && \
	a2ensite devops-demystified

# Start apache
CMD apache2ctl -D FOREGROUND
