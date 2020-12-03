FROM openjdk:11

RUN mkdir conf && mkdir logs && mkdir libs
COPY build/libs/* /libs/

CMD /usr/java/default/bin/java\
 -Xmx2g -Dfile.encoding=UTF-8\
 -Duser.timezone=Europe/Moscow\
 -Djava.util.logging.config.file=/conf/logback.xml\
 -cp /libs/*:conf edu.roadmaps.core.Application\
 --spring.config.location=classpath:/application.properties

EXPOSE 8080