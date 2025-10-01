
# README

- For detailed instructions, please refer to the original repo and video.

## Credits

- This repo was constructed following the course by Sai Upadhyayula on [YouTube-spring-boot-3-microservices-course](https://www.youtube.com/watch?v=yn_stY3HCr8)
- The original code repo is available at [GitHub-spring-boot-3-microservices-course](https://github.com/SaiUpadhyayula/spring-boot-3-microservices-course)

### Notable Changes from Course

- Ports have been changed to avoid conflicts with existing services on my machine.
- Deprecated code has been updated to be compatible with the latest versions of Spring Boot and related technologies.
- Using .http for API testing instead of Postman.

## Config Notes

### Resources

- [Spring Initializr](https://start.spring.io/)

### Port Mappings

| Service                   | Port   | Note              |
|---------------------------|--------|-------------------|
| APIGateway                | 8088   |                   |
| Keycloak                  | 8181   |                   |
| Frontend.Angular          | 4200   |                   |
| OrderService              | 8083   |                   |
| OrderService.MySQL        | 3306   |                   |
| ProductService            | 8081   |                   |
| ProductService.MongoDB    | 27017  |                   |
| InventoryService          | 8082   |                   |
| InventoryService.MySQL    | 3307   |                   |
| Schema-Registry           | 8085   | ConfluentInc Package  |
| ZooKeeper                 | 2181   | Kafka Cluster Orchestrator                  |
| Kafka-Broker              | 9092   |                   |
| Kafka-Broker              | 29092  |                   |
| Kafka-UI                  | 8086   |                   |
| Zipkin                    | 9411   | Trace Exporter     |
| Prometheus                | 9090   | Metric Collector  |
| Grafana                   | 3001   | Dashboard         |
| Grafana-Loki              | 3101   | Log Aggregator    |
| Grafana-Tempo             | 3110   | Distribute Tracing |
