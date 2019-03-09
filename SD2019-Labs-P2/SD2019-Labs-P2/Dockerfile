FROM maven:3.5.3-jdk-8-alpine as target

WORKDIR /build

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src/ /build/src/

RUN mvn clean compile assembly:single

RUN ls -lR /build/


FROM smduarte/sd18-tp1-openjdk8

MAINTAINER Sérgio Duarte <smd@fct.unl.pt>

WORKDIR /home/sd

COPY --from=target /build/target/*jar-with-dependencies.jar sd19-tp1.jar



#ARG JAR_FILE
#ADD target/${JAR_FILE} .

#ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/myservice/myservice.jar"]
