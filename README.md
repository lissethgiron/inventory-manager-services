# inventory-manager-services

## Docker container

### Generate docker image

    docker build -t inventory-manager-services:latest .

#### Run the container using docker:

    docker run -t -d -p 8080:8080 inventory-manager:latest

### Delete docker container

    docker rmi inventory-manager
