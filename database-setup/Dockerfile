FROM openjdk:11.0.2-jre-stretch

ADD ["target/database-setup-0.0.1-SNAPSHOT.jar", "database-setup-0.0.1-SNAPSHOT.jar"]
EXPOSE 16000
RUN sh -c 'touch /database-setup-0.0.1-SNAPSHOT.jar'

ENV JAVA_OPTS=""

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /database-setup-0.0.1-SNAPSHOT.jar" ]
