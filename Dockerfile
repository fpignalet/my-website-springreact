FROM openjdk:latest

COPY target/Core-0.0.1-SNAPSHOT.jar /usr/src/Core-0.0.1-SNAPSHOT.jar

CMD java -cp /usr/src/Core-0.0.1-SNAPSHOT.jar com.core.CoreApplication
