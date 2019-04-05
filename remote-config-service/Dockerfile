FROM openjdk:11.0.2-jre-stretch

ENV PROFILE="dev"

RUN ["mkdir", "/root/remote-config-service"]
COPY target/remote-config-service-0.0.1-SNAPSHOT.jar /root/remote-config-service/

ENTRYPOINT java -Dspring.profiles.active=$PROFILE -jar /root/remote-config-service/remote-config-service-0.0.1-SNAPSHOT.jar

EXPOSE 9000
