# template-service-java
simple template service built from a spring sample.

## Known Issues

PLease note *Section 5* Swagger has a few bugs due to updates to Swagger 3.

| Issue         |     Description    |
|---------------|:-----------------:|
| Disabling swagger config via compose turns on V3 UI | Turning off swagger via application-{target}.yml turns off `SWAGGER` two docket and the V3 docket is used in its place. Need to update to V3 docket so it is not defaulted to and turned off when the configuration is not loaded. Suspect v3 docket disable is different. This needs to be updated. Using the SWAGGER_2 docket does not work. Alternative, downgrade to Swagger Springfox 2.9.2.     |
| Swagger custom porperties is not loading from `locale-en.yml` |   Not entirely sure what is going on. For some reason the SwaggerProperties.java file is not picking up external file. This works fine if the swagger section is located in the application.yml file. File access or file structure issues are suspected. Probably something simple being overlooked.       |
| Minikube compose does not like volume mounts.   | After you run `eval $(minikube -p minikube docker-env)` and then `make image` it will fail. The work around is to run `make image-np` No Package. This skips compile where a volume is not used. You will have to run `make image` outside the context of minikube to get the target jar. You can alos run `mvn package`.      |
| Kubernets run is broken | Greeting app requires a connection to a postgres DB. Next section will add a kubernetes build of postgres using a persistent volume.      |
| intentionally | blank      |