FROM openjdk:11

RUN mkdir conf && mkdir logs && mkdir libs
ADD build/libs/* /libs
ENTRYPOINT ["java","-jar","libs/roadmaps_core-v0.1-SNAPSHOT.jar"]

EXPOSE 8080