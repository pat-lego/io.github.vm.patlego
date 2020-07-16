#
# Tesseract 4 OCR Runtime Environment - Docker Container
#
FROM ubuntu:18.04

RUN apt-get update && apt-get install -y software-properties-common && add-apt-repository -y ppa:alex-p/tesseract-ocr
RUN apt-get update && apt-get install -y tesseract-ocr-all 

RUN apt-get update && apt-get install -y openjdk-11-jdk

RUN mkdir -p /opt/karaf
RUN mkdir -p /home/root/.m2/repository
WORKDIR /opt/karaf

ENV EXTRA_JAVA_OPTS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
ENV KARAF_HOME /opt/karaf
ENV JAVA_HOME /usr
ENV PATH $PATH:$KARAF_HOME/bin

EXPOSE 8181 8101 1099 44444 5005

CMD exec karaf run