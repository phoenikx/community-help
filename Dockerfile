FROM openjdk:7
WORKDIR /opt/cogiv
RUN apt-get install vi tar wget

COPY target/cogiv.jar /opt/cogiv
COPY deployment/application.yml /opt/cogiv
COPY deployment/start_process.sh /opt/cogiv

RUN chmod +x /opt/cogiv/start_process.sh

CMD [ "./start_process.sh" ]