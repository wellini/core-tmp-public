FROM openjdk:18-alpine
RUN apk add maven

WORKDIR /opt/core
COPY ./pom.xml .
RUN mvn dependency:go-offline

COPY . .
EXPOSE 8080
CMD ["sh", "./entrypoint.sh" ]
