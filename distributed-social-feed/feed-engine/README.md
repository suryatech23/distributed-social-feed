# Distributed Social Feed Architecture

A high-performance backend architecture engineered to process, store, and serve high-volume social feed data with sub-10ms read latency. Built using Spring Boot, this system implements asynchronous event-driven design patterns to decouple data ingestion from data persistence.

## System Architecture

* **API Gateway & Routing (Spring Boot / Java 17):** Exposes RESTful endpoints for client ingestion and feed retrieval.
* **Event Streaming (Apache Kafka - KRaft Mode):** Acts as the primary message broker. Incoming HTTP payloads are immediately serialized and pushed to a Kafka topic, allowing the API to return a `202 Accepted` response instantly rather than blocking the thread to wait for database writes.
* **Asynchronous Persistence (Kafka Consumers):** Background worker threads consume the Kafka stream and persist the data into the storage layer.
* **Distributed Storage (MongoDB):** A NoSQL document database chosen for its high-write throughput and flexible schema, ideal for unstructured social data.
* **In-Memory Caching (Redis):** Intercepts read requests (`GET /api/posts`). If a cache miss occurs, data is fetched from MongoDB and cached in Redis. Subsequent read requests are served entirely from RAM, drastically reducing latency and database load.
* **Containerization (Docker Compose):** The entire infrastructure layer (Kafka, Redis, MongoDB) is orchestrated via Docker Compose for deterministic local deployment and testing.

## Tech Stack
* Java 17, Spring Boot (Web, Data MongoDB, Data Redis, Kafka)
* Apache Kafka (Confluent Local)
* Redis 7
* MongoDB 6
* Docker & Docker Compose