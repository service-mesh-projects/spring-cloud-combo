FROM openjdk:8-jre-alpine

ENV JAVA_OPTS=" -Xms256M -Xmx512M"

ADD https://github.com/alibaba/Sentinel/releases/download/1.5.0/sentinel-dashboard-1.5.0.jar  /

COPY config/* config/
COPY docker-entrypoint.sh .

EXPOSE 8080

ENTRYPOINT ["sh", "docker-entrypoint.sh"]