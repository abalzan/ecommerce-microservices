FROM openjdk:11.0.2-jre-stretch

ADD ["target/address-api-0.0.1-SNAPSHOT.jar", "address-api-0.0.1-SNAPSHOT.jar"]
EXPOSE 18300
RUN sh -c 'touch /address-api-0.0.1-SNAPSHOT.jar'

ENV JAVA_OPTS=""

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /address-api-0.0.1-SNAPSHOT.jar" ]


HEALTHCHECK --interval=5m --timeout=3s \
    CMD curl -f http://localhost:8094/actuator/health || exit 1
