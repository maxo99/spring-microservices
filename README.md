
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

| Service                   | Port   |
|---------------------------|--------|
| API Gateway               | 8088   |
| Product Service           | 8081   |
| Inventory Service         | 8082   |
| Order Service             | 8083   |
| Angular-Frontend          | 4200   |
| MongoDB-ProductService    | 27017  |
| MySQL-OrderService        | 3306   |
| MySQL-InventoryService    | 3306   |
| Kafka-Broker              | 9092   |
| Kafka-Broker              | 29092  |
| Kafka-UI                  | 8086   |
| ZooKeeper                 | 2181   |
| Schema-Registry           | 8085   |
