FROM registry.cn-north-1.huaweicloud.com/hwcse/dockerhub-java:8-jre-alpine

RUN mkdir -p /maven/acmeair/

COPY ./*-exec.jar  /maven/acmeair/

ENTRYPOINT java $JAVA_OPTS $AK $SK -jar /maven/acmeair/$JAR_NAME-exec.jar
