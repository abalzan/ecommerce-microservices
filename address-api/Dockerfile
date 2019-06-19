FROM openjdk:11.0.2-jre-stretch

ENV PROFILE="docker"

ADD ["target/address-api-0.0.1-SNAPSHOT.jar", "address-api-0.0.1-SNAPSHOT.jar"]
EXPOSE 18300
RUN sh -c 'touch /address-api-0.0.1-SNAPSHOT.jar'

ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=$PROFILE -jar /address-api-0.0.1-SNAPSHOT.jar" ]


HEALTHCHECK --interval=5m --timeout=3s \
    CMD curl -f http://localhost:8094/actuator/health || exit 1
