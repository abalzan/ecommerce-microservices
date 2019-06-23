FROM openjdk:11.0.2-jre-stretch

ENV JAVA_OPTS=""

ADD ["target/eureka-service-discovery-0.0.1-SNAPSHOT.jar", "eureka-service-discovery-0.0.1-SNAPSHOT.jar"]

RUN sh -c 'touch /"eureka-service-discovery-0.0.1-SNAPSHOT.jar"'

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /eureka-service-discovery-0.0.1-SNAPSHOT.jar" ]

EXPOSE 8761
