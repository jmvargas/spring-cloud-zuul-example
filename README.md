# spring-cloud-zuul-example

## Steps to run it

1) Run mysql with docker

```
docker-compose up -d
```

2) Start eureka service

3) Start zuul service

4) Start final-project service

Next you can visit these URLs to check the final-project service:

http://localhost:9090/routes

http://localhost:9090/api/student-by-address/swagger-ui.html

http://localhost:9090/api/student-by-service/swagger-ui.html