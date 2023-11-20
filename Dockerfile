FROM amazoncorretto:17.0.8-alpine3.18

ADD app/build/libs/app.jar order-service.jar
ENTRYPOINT [ "java", "-jar", "order-service.jar" ]