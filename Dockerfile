FROM openjdk:8-jdk-alpine

EXPOSE 8080/tcp

COPY build/libs/*.jar /opt/app.jar

ENV JAVA_OPTS="${JAVA_OPTS} -Xms512m -Xmx512m"

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar","/opt/app.jar"]
