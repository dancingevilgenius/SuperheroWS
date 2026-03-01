FROM  eclipse-temurin:17
VOLUME /tmp
ADD target/superhero-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
