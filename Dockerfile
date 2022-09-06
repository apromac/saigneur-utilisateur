# ======================================================================
# DOCKERFILE
# CONSTRUCTION DE L'IMAGE DOCKER DU MICROSERVICE "saigneur-utilisateur"
# ======================================================================
FROM openjdk:11-alpine
LABEL maintainer = "apromac <abraham.tiene@apromac.ci>"

RUN mkdir /usr/local/microservice \
&& mkdir /usr/local/microservice/msaigneur

WORKDIR /usr/local/microservice/msaigneur
COPY target/*.jar saigneur-utilisateur.jar

EXPOSE 9001
ENTRYPOINT ["java", "-jar", "saigneur-utilisateur.jar"]
