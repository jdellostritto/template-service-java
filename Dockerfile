FROM openjdk:11.0.8-jre-slim

# Expose operational port and the health port
EXPOSE 8700 9001

# make the dir we need
RUN mkdir -p /app

# change working directory
WORKDIR /app

# copy jar into image
COPY target/*.jar /app/

# get our config dir
RUN mkdir -p /app/config

# deploy properties
COPY target/classes/application.yml /app/config

# create a symlink because we have to have a single file to pass to "java -jar some.jar"
RUN ln -s $(ls -1 *.jar) app.jar

ENV apm_agent -javaagent:newrelic/newrelic.jar 
CMD java $([ "$ENABLE_APM" == "true" ] && echo $apm_agent && echo '-Dnewrelic.environment='$ENVIRONMENT) -Xms128m -Xmx128m -jar app.jar
