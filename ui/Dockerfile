FROM ubuntu:latest
RUN apt-get update
RUN apt-get -y install apache2
RUN a2enmod userdir
RUN a2enmod headers
RUN a2enmod proxy
RUN a2enmod proxy_http
RUN ln -s /build/dist/ui /app
COPY ./apache.conf /etc/apache2/sites-available/devops-demystified.conf
RUN a2dissite 000-default
RUN a2ensite devops-demystified
CMD apache2ctl -D FOREGROUND
