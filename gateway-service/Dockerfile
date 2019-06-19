FROM openjdk:11.0.2-jre-stretch

ENV PROFILE="docker"

COPY wait-for-it.sh /usr/local/
RUN chmod +x /usr/local/wait-for-it.sh

ADD ["target/gateway-service-0.0.1-SNAPSHOT.jar", "gateway-service-0.0.1-SNAPSHOT.jar"]
RUN sh -c 'touch /gateway-service-0.0.1-SNAPSHOT.jar'


ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=$PROFILE -jar /gateway-service-0.0.1-SNAPSHOT.jar" ]

EXPOSE 8888