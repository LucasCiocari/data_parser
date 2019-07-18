FROM maven:3.6.1-jdk-8
COPY . /
ENV HOMEPATH="/home"
RUN mkdir -p $HOMEPATH/data/in
COPY example.dat /home/data/in/example.dat
RUN mvn install
RUN mvn package
CMD [ "java", "-cp", "/target/dataparser-1.0-SNAPSHOT.jar", "app.App"]