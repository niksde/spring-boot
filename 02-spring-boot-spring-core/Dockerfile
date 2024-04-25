FROM maven:3.8.5-openjdk-17-slim

WORKDIR /app
COPY . /app

RUN mvn clean package
COPY /target/*.jar /app/app.jar

RUN apt-get update && apt-get install -y python3 python3-pip python-is-python3
RUN pip install -r /app/src/main/resources/scripts/requirements.txt

EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]
