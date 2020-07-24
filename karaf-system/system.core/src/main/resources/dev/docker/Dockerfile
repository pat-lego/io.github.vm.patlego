FROM openjdk:11
RUN mkdir -p /home/root/.m2/repository
ENV KARAF_INSTALL_PATH /opt
ENV KARAF_HOME $KARAF_INSTALL_PATH/patlego.vm
ENV PATH $PATH:$KARAF_HOME/bin
ENV EXTRA_JAVA_OPTS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
COPY patlego.vm $KARAF_HOME
EXPOSE 8101 1099 44444 8181 5005
CMD ["karaf", "run"]
