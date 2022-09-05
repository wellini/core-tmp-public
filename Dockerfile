FROM docker.io/eclipse-temurin:17-jdk as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY service/pom.xml .

RUN ./mvnw dependency:go-offline

COPY service/src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# App is compiled now - can copy non-code files
COPY .env.example .
COPY entrypoint.sh .

FROM docker.io/eclipse-temurin:17-jre

ARG BUILD_WORKDIR=/workspace/app
ARG DEPENDENCY_IN_BUILD=$BUILD_WORKDIR/target/dependency

COPY --from=build $DEPENDENCY_IN_BUILD/BOOT-INF/lib /app/lib
COPY --from=build $DEPENDENCY_IN_BUILD/META-INF /app/META-INF
COPY --from=build $DEPENDENCY_IN_BUILD/BOOT-INF/classes /app

COPY --from=build $BUILD_WORKDIR/.env.example .
COPY --from=build $BUILD_WORKDIR/entrypoint.sh .

CMD ["sh", "./entrypoint.sh"]
