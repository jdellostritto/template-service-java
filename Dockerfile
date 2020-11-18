#*NIX 
FROM openjdk:11.0.8-jre-slim

#WINFROM winamd64/openjdk:11.0.9-jdk-nanoserver

# Expose operational port and the health port
EXPOSE 8700 9001

# make the dir we need
# *NIX
RUN mkdir -p /app
# WinRUN mkdir -p \app

# change working directory
WORKDIR /app

# copy jar into image
COPY target/*.jar /app/

# get our config dir
# *NIX 
RUN mkdir -p /app/config
# WinRUN mkdir -p \app\config

# deploy properties
COPY target/classes/application.yml /app/config

# create a symlink because we have to have a single file to pass to "java -jar some.jar"
# *NIX 
RUN ln -s $(ls -1 *.jar) app.jar

#Win CMD java -Xms128m -Xmx128m -jar template-service-java-0.0.1-SNAPSHOT.jar
# *NIX 
CMD java -Xms128m -Xmx128m -jar app.jar
