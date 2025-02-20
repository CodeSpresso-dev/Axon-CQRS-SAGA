# Axon-CQRS-SAGA

## Project Structure

#### Java Version : Java 21
#### Spring Boot Version : 3.4.2
#### Spring Cloud Version : 2024.0.0
#### Eureka Discovery Server
#### Eureka Discovery Client
#### Spring Cloud Gateway
#### Axon Server Version : 2024.2.3

---

### <u>Product Service</u>
#### The ProductService is a simple REST API for products in a microservice architecture. It provides endpoints for creating, reading, updating, and deleting products.

### <u>Discovery Server</u>
#### The DiscoveryServer is typically a Spring Cloud Eureka Server used for service discovery in a microservice architecture. It helps different services register themselves and discover other services dynamically.

### <u>Api Gateway</u>
#### API Gateway for a microservices ecosystem, responsible for routing requests to appropriate services registered in Eureka Discovery Server. It helps manage authentication, logging, and load balancing.
##### Features
1. Routes incoming requests to microservices
2. Integrates with Eureka for service discovery
3. Centralized entry point for the system 

### <u>Axon Server</u>
#### You can manually download the Axon Server JAR file or Docker image from: [AxonServer](https://www.axoniq.io/download)
#### The following Docker command allows you to pull the image and run it in a containerized environment
~~~
docker run --name axonserver -p 8024:8024 -p 8124:8124 -v "[YourLocalPath]/data":/data -v "[YourLocalPath]/eventdata":/eventdata -v "[YourLocalPath]/config":/config axoniq/axonserver
~~~