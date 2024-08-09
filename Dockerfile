FROM openjdk:17

COPY ./build/libs/junction-0.0.1-SNAPSHOT.jar /junction-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/junction-0.0.1-SNAPSHOT.jar"]
