# Hoot Posts API
The posts management service for [Hoot](https://github.com/chrisashwalker/hoot) - a tiny Human Resources management system built upon microservices.  
Developed with Java Spring.

## Run
```
./gradlew bootRun
```

## Build
```
./gradlew build
```

## Build Docker image
```
docker build -t hoot-api-posts .
```

## Create and run docker container
```
docker run --name hoot-api-posts-container -p 8002:8002 hoot-api-posts 
```

## Scaffolded with
[start.spring.io](start.spring.io)
