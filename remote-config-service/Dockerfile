FROM openjdk:11.0.2-jre-stretch

ENV PROFILE="dev"

RUN ["mkdir", "/root/remote-config-service"]
COPY target/remote-config-service-0.0.1-SNAPSHOT.jar /root/remote-config-service/

ENTRYPOINT java -Dspring.profiles.active=$PROFILE -jar /root/product-api/remote-config-service-0.0.1-SNAPSHOT.jar

EXPOSE 9000

HEALTHCHECK --interval=5m --timeout=3s \
    CMD curl -f http://localhost:9001/actuator/health || exit 1
