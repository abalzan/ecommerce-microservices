FROM openjdk:11.0.2-jre-stretch

ENV PROFILE="dev"

ADD ["target/remote-config-service-0.0.1-SNAPSHOT.jar", "remote-config-service-0.0.1-SNAPSHOT.jar"]
EXPOSE 9000 9001
ENV JAVA_OPTS="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=9001,suspend=n"
RUN sh -c 'touch /remote-config-service-0.0.1-SNAPSHOT.jar'

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=$PROFILE -jar /remote-config-service-0.0.1-SNAPSHOT.jar" ]
