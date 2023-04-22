## Creation
Create with start.spring.io with a Spring Web dependency.

## Execute app
./gradlew bootRun

## Create JAR
./gradlew build

## Create Docker image
docker build -t hoot-api-posts .

## Run Docker image
docker run --rm --name hoot-api-posts-container -p 8002:8002 hoot-api-posts 