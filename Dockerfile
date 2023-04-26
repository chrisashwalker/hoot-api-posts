FROM gradle:jdk17-alpine AS BUILD
WORKDIR /usr/app
COPY . .
RUN gradle build

FROM amazoncorretto:17-alpine
WORKDIR /usr/app
COPY --from=BUILD /usr/app/build/libs/*SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
