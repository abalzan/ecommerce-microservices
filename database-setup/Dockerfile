FROM openjdk:11.0.2-jre-stretch

ENV PROFILE="docker"

ADD ["target/database-setup-0.0.1-SNAPSHOT.jar", "database-setup-0.0.1-SNAPSHOT.jar"]
EXPOSE 16000
RUN sh -c 'touch /database-setup-0.0.1-SNAPSHOT.jar'

ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=$PROFILE -jar /database-setup-0.0.1-SNAPSHOT.jar" ]
