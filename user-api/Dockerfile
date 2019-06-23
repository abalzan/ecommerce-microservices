FROM openjdk:11.0.2-jre-stretch

ADD ["target/user-api-0.0.1-SNAPSHOT.jar", "user-api-0.0.1-SNAPSHOT.jar"]
EXPOSE 18200
RUN sh -c 'touch /user-api-0.0.1-SNAPSHOT.jar'

ENV JAVA_OPTS=""

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /user-api-0.0.1-SNAPSHOT.jar" ]

HEALTHCHECK --interval=5m --timeout=3s \
    CMD curl -f http://localhost:8093/actuator/health || exit 1
