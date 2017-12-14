FROM java:8
MAINTAINER Eva Lopez Puente, Lorena Martin Tejera
EXPOSE 8080
VOLUME /tmp
ADD /target/PracticaServerMySql-0.0.1-SNAPSHOT.jar practicaserver.jar
ENTRYPOINT ["java","-jar","practicaserver.jar"]
