# Springboot RabbitMQ Communication
A basic exposure to RabbitMQ communication between Microservices

## What is this?
This is a simple demo project created using Springboot which utilizes a non-http(s) way of communication between the Microservices. This might be useful to others or to my future self. There are hundreds of tutorails which explain about communication b/w Microservices using HTTP but very minimal when it comes to non-http(s) that is AMQP, so felt this is not exposed much so here we are.

## Architecture
1) Entity - A simple POJO which contains bootstrap methods (Constructors, Getters/Setters)
2) Producer - Responsible for creating a POJO object, Encrpyting and transfering to the MQ
3) RabbitMQConfig - Contains all the Rabbit MQ configuration files required for the process
4) Consumer - Responsible for collecting the data from MQ, Decrypting and storing the data in a list for simplicity

### Exposed End-Points:
1) /send - Sends the encrypted data to the MQ
2) /getMessage - Displays the list populated by Consumer on the page

## What does it include?
1) Communication using RabbitMQ
2) Slapped a decent AES Encrpytion/Decrpytion algorithm which helps secure the data while communicating
3) Single Microservice which contains Producer and Consumer (may it be the readers wish to implement these separately) 

## What is planned next?
I will be constantly updating this repo with more useful information that I might learn and document it using this repo. This will definitely help others and also myself once I return back to this field after a few days.

Planned Features Include:
1) ~~Implementing Google Protobuf to provider data serliazation~~
2) ~~Implementing Swagger-UI for API documentation~~
3) Implement a proper Consumer Microservice separately which communicates via RabbitMQ with Serialized Encrypted Data
