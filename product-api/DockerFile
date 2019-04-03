FROM openjdk:11.0.2-jre-stretch

ENV PROFILE="dev"

RUN ["mkdir", "/root/product-api"]
COPY target/product-api-0.0.1-SNAPSHOT.jar /root/product-api/

ENTRYPOINT java -Dspring.profiles.active=$PROFILE -jar /root/product-api/product-api-0.0.1-SNAPSHOT.jar

EXPOSE 18100

HEALTHCHECK --interval=5m --timeout=3s \
    CMD curl -f http://localhost:8091/actuator/health || exit 1
