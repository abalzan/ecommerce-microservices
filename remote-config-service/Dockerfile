FROM openjdk:11.0.2-jre-stretch

RUN ["mkdir", "/root/remote-config-service"]
COPY target/remote-config-service-0.0.1-SNAPSHOT.jar /root/remote-config-service/

ENTRYPOINT java -jar /root/remote-config-service/remote-config-service-0.0.1-SNAPSHOT.jar

EXPOSE 9000 9001