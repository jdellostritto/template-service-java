# LINUX 
FROM openjdk:11.0.8-jre-slim
# WINDOWS FROM winamd64/openjdk:11.0.9-jdk-nanoserver

# Expose operational port and the health port
EXPOSE 8700 9001

# Make the directory we need
# LINUX
RUN mkdir -p /app
# WINDOWS RUN mkdir -p \app

# change working directory
WORKDIR /app

# copy jar into image
COPY target/*.jar /app/

# Get our config directory
# LINUX 
RUN mkdir -p /app/config
# WINDOWS RUN mkdir -p \app\config

# deploy properties
COPY target/classes/application.yml /app/config

# create a symlink because we have to have a single file to pass to "java -jar some.jar"
# LINUX 
RUN ln -s $(ls -1 *.jar) app.jar

# LINUX 
CMD java -Xms128m -Xmx128m -jar app.jar
# WINDOWS CMD java -Xms128m -Xmx128m -jar template-service-java-0.0.2-SNAPSHOT.jar
