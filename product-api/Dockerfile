FROM openjdk:11.0.2-jre-stretch

ENV PROFILE="dev"

ADD ["target/product-api-0.0.1-SNAPSHOT.jar", "product-api-0.0.1-SNAPSHOT.jar"]
EXPOSE 18100
RUN sh -c 'touch /product-api-0.0.1-SNAPSHOT.jar'

ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=$PROFILE -jar /product-api-0.0.1-SNAPSHOT.jar" ]


HEALTHCHECK --interval=5m --timeout=3s \
    CMD curl -f http://localhost:8091/actuator/health || exit 1
