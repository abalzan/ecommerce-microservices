FROM openjdk:11.0.2-jre-stretch

ENV PROFILE="dev"

RUN ["mkdir", "/root/eureka-service-discovery"]
COPY target/eureka-service-discovery-0.0.1-SNAPSHOT.jar /root/eureka-service-discovery/

ENTRYPOINT java -Dspring.profiles.active=$PROFILE -jar /root/eureka-service-discovery/eureka-service-discovery-0.0.1-SNAPSHOT.jar

EXPOSE 8761
