FROM adoptopenjdk/maven-openjdk11
COPY src /build/src
COPY pom.xml /build
WORKDIR /build
RUN mvn dependency:go-offline

RUN mvn package
