FROM adoptopenjdk/maven-openjdk11
EXPOSE 8080
COPY ./ /build/
#COPY pom.xml /build
WORKDIR /build
RUN mvn dependency:go-offline
