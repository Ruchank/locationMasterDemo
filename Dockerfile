FROM openjdk:17
EXPOSE 8081
ADD target/locationMasterDemo-0.0.1-SNAPSHOT.jar locationMasterDemo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/locationMasterDemo-0.0.1-SNAPSHOT.jar"]