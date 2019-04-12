FROM openjdk:11.0.2-jre-stretch

ENV PROFILE="docker"

RUN ["mkdir", "/root/category-api"]
COPY target/category-api-0.0.1-SNAPSHOT.jar /root/category-api/

ENTRYPOINT java -Dspring.profiles.active=$PROFILE -jar /root/category-api/category-api-0.0.1-SNAPSHOT.jar

EXPOSE 18100

HEALTHCHECK --interval=5m --timeout=3s \
    CMD curl -f http://localhost:8092/actuator/health || exit 1
