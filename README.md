# Dockerized DevOps DeMystified

## What is this?

Last year for the Innovate VA conference in Richmond, VA, SingleStone gave a talk titled DevOps DeMystified. The goal of the talk was to introduce the basic tenants of the DevOps movement to business and technical users who had the word used but not explained. At the heart of the talk was an interactive demonstration of the power of infrastructure automation and continuous delivery. Using tools such as Chef, AWS EC2, Cloud Formation and DynamoDB, Jenkins CI and GitHub, the team demonstrated the creation of an application environment in a fully automated fashion by running a single command.

This repo will attempt to recreate the spirit of the first demo using Linux containers (Docker) instead of Configuration Management tools (Chef).

## Getting Started

### Pre-requistes

1. [Docker](https://docs.docker.com/installation/#installation) installed.
2. [Docker Compose](https://docs.docker.com/compose/install/) installed.
3. [Twilio](https://www.twilio.com/try-twilio) account.

### Running the containers

1. Copy api/TEMPLATE_application.properties to api/application.properties.
2. Open the application.properties file and enter your Twilio authToken, sid and phoneNumber.
3. From the root folder, type `docker-compose up`.
