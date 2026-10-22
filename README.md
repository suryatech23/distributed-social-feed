# Distributed Social Feed

This project is a student-friendly distributed application designed to demonstrate social feed data processing using modern backend services.

## What This Project Does

The application is meant to show how a feed system can be built with event-driven communication, data persistence, and queue-based processing. It is a useful example of distributed system design in a simplified form.

### Demo flow

1. Start the infrastructure services.
2. Simulate new feed events.
3. Send data through the feed processing path.
4. Observe how the system stores and processes items.
5. Review the backend flow and service interactions.

## Architecture Overview

```text
Client / App Layer
      |
      v
API / Feed Service
      |
      +--> Kafka event processing
      +--> Redis caching
      +--> MongoDB storage
      |
      v
Distributed feed pipeline
```

## Tech Stack

- Node.js
- Express
- Kafka
- Redis
- MongoDB
- Docker Compose

## Deployment Notes

This project is best run with Docker Compose for local development or a hosted environment for distributed services.

Recommended approach:

- run the required services from `docker-compose.yml`
- configure environment variables for service access
- deploy backend services in a hosted environment if needed
- keep Redis, MongoDB, and Kafka separation explicit in production setups

## Student Notes

This project is a great example of how distributed systems concepts can be understood through a social feed application. It combines event processing, cache usage, storage, and service orchestration, which makes it valuable for learning backend architecture.

## Possible Future Improvements

- add user authentication
- add feed ranking and recommendation logic
- add real-time notifications
- add analytics and feed metrics
- make the service production-ready with monitoring and alerts
