FROM openjdk:11.0.2-jre-stretch

ADD ["target/category-api-0.0.1-SNAPSHOT.jar", "category-api-0.0.1-SNAPSHOT.jar"]
EXPOSE 18100
RUN sh -c 'touch /category-api-0.0.1-SNAPSHOT.jar'

ENV JAVA_OPTS=""

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /category-api-0.0.1-SNAPSHOT.jar" ]

HEALTHCHECK --interval=5m --timeout=3s \
    CMD curl -f http://localhost:8092/actuator/health || exit 1
