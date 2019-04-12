FROM openjdk:11.0.2-jre-stretch

RUN ["mkdir", "/root/eureka-service-discovery"]
COPY target/eureka-service-discovery-0.0.1-SNAPSHOT.jar /root/eureka-service-discovery/

ENTRYPOINT java -jar /root/eureka-service-discovery/eureka-service-discovery-0.0.1-SNAPSHOT.jar

EXPOSE 8761
