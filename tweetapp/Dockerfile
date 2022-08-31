FROM openjdk:11
LABEL maintainer="sambit"
ADD target/tweetapp.jar tweetapp-docker.jar
ENTRYPOINT ["java", "-jar", "tweetapp-docker.jar"]