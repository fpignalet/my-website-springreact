FROM openjdk:latest
EXPOSE 8080
ARG DEPENDENCY=target/classes/static/*
COPY ${DEPENDENCY} /
COPY target/libs/libextfacade.so /lib
ADD /target/Core-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
#OK, SO NOW APPLICATION STARTS, but there is issues to access ressources (for exmple EEngJSONFiles "target/classes/static/data/")
#UNDER INVESTIGATION...
