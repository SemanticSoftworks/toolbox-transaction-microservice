FROM java:8
VOLUME /tmp
ADD transaction-microservice-0.0.1-SNAPSHOT.jar transaction-microservice.jar
EXPOSE 8092
RUN sh -c 'touch /transaction-microservice.jar'
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /transaction-microservice.jar" ]
