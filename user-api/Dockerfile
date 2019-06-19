FROM openjdk:11.0.2-jre-stretch

ENV PROFILE="docker"

ADD ["target/user-api-0.0.1-SNAPSHOT.jar", "user-api-0.0.1-SNAPSHOT.jar"]
EXPOSE 18200
RUN sh -c 'touch /user-api-0.0.1-SNAPSHOT.jar'

ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=$PROFILE -jar /user-api-0.0.1-SNAPSHOT.jar" ]

HEALTHCHECK --interval=5m --timeout=3s \
    CMD curl -f http://localhost:8093/actuator/health || exit 1
