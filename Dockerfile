FROM openjdk:8
WORKDIR /opt/cogiv
COPY target/cogiv.jar /opt/cogiv
COPY src/main/resources/application.yml /opt/cogiv
COPY deployment/start_process.sh /opt/cogiv
RUN chmod +x /opt/cogiv/start_process.sh

CMD [ "./start_process.sh" ]