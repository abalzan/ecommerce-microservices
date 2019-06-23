FROM openjdk:11.0.2-jre-stretch

ADD ["target/gateway-service-0.0.1-SNAPSHOT.jar", "gateway-service-0.0.1-SNAPSHOT.jar"]
RUN sh -c 'touch /gateway-service-0.0.1-SNAPSHOT.jar'

ENV JAVA_OPTS=""

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /gateway-service-0.0.1-SNAPSHOT.jar" ]

EXPOSE 8888