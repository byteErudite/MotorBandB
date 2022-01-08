FROM adoptopenjdk:13-jre-hotspot
ADD target/parkingReservation-0.0.1-SNAPSHOT.jar parkingReservation-0.0.1-SNAPSHOT.jar
ADD logback.xml logback.xml
ENTRYPOINT ["java", "-jar", "parkingReservation-0.0.1-SNAPSHOT.jar"]