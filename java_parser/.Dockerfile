FROM maven:3.6.1-jdk-8
COPY . /
RUN export HOME_PATH="/home"
RUN mkdir -p $HOME_PATA/data
RUN mvn install
RUN mvn package
CMD [ "java", "-cp", "/target/dataparser-1.0-SNAPSHOT.jar", "app.App"]