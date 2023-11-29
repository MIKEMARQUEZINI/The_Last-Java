# The_Last-Java

## Description

This repository was created based on all the fundamentals taught by Professor [Emerson](https://github.com/EmerF). The project includes proposals for CRUD implementation, microservices, DevOps, gitFlow, and other essential concepts.

## Features

- **CRUD:** Implementation of basic Create, Read, Update, and Delete operations.
- **Microservices:** Microservices-oriented architecture for modularity and scalability.
- **DevOps:** Continuous integration, continuous delivery, and test automation.
- **GitFlow:** Utilization of the GitFlow branching model for version control organization.

## Prerequisites

Before you begin, ensure you have installed:

- [Docker Desktop](https://www.docker.com/products/docker-desktop)
- [Docker Engine (WSL)](https://docs.docker.com/desktop/install/linux-install-wsl/)
- [Docker Compose](https://docs.docker.com/compose/install/)
  
Install Docker Compose using the following commands:

```bash
sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
docker-compose --version
```

Additionally, create Docker images for RabbitMQ and MongoDB:
### Create RabbitMQ Docker image with management plugin
```bash
docker run -d --name rabbitmq-container -p 5672:5672 -p 15672:15672 rabbitmq:management
```

### Create MongoDB Docker image
```bash
docker run -d -p 27017:27017 --name meumongo mongo
```

## Installation
- Clone this repository: `git clone https://github.com/MIKEMARQUEZINI/The_Last-Java.git` 
- Navigate to the project directory: `cd your-repository`

## Run the installation commands: 

To build and run the application using Docker, execute the following commands:
`Remove existing image (if any), build a new image, and run the container`
```bash
docker image rm -f the-last-image; docker build -t the-last-image . ; docker run -d -p 8383:8383 the-last-image
```

## Steps to Execute via Docker Compose

1. Run `mvn clean` to clean outdated JARs.
2. Run `mvn clean install -DskipTests` to install with new configurations.
3. Execute `docker-compose up --build` to build and start the application.
- Stop the execution with `docker-compose down`.

  


