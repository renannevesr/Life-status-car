FROM openjdk:8-alpine

COPY target/uberjar/life-status-car.jar /life-status-car/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/life-status-car/app.jar"]
