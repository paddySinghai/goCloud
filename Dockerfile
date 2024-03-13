FROM openjdk:21
ADD target/goCloud-*.jar goCloud.jar
ENTRYPOINT ["java","-jar","goCloud.jar"]
