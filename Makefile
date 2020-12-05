.PHONY: run stop delete image package clean build kube-apply-bash kube-delete-bash kube-apply-ps kube-delete-ps image-np image-db

# name of the image to build
IMAGE ?= acme.io/template-service-java
BUILD ?= latest

DOCKER_COMPOSE ?= docker-compose
RUN_CONFIG ?= -f docker-compose.yml

COMPOSE_PGADMIN ?= -f docker-compose.pgadmin.yml 
COMPOSE_POSTGRES ?= -f docker-compose.postgres.yml 
COMPILE_CONFIG ?= -f docker-compose.compile.yml
LOCAL_CONFIG ?= -f docker-compose.local.yml

COMPOSE ?= $(DOCKER_COMPOSE) $(RUN_CONFIG)
MAVEN ?= $(COMPOSE) $(COMPILE_CONFIG) run --rm -w /usr/src/app app mvn


build:
	$(MAVEN) compile
	$(COMPOSE) stop
	$(COMPOSE) rm -f

clean:
	$(MAVEN) clean
	$(COMPOSE) stop
	$(COMPOSE) rm -f

package:
	$(MAVEN) package -e -Dmaven.test.skip=true
	$(COMPOSE) stop
	$(COMPOSE) rm -f

test:
	$(MAVEN) test
	$(COMPOSE) stop
	$(COMPOSE) rm

image: package
	docker build -t $(IMAGE):$(BUILD) .

image-np: 
	docker build -t $(IMAGE):$(BUILD) .

run:
	$(COMPOSE) $(LOCAL_CONFIG) $(COMPOSE_PGADMIN) $(COMPOSE_POSTGRES) up

run-db:
	$(COMPOSE) up -d db; \
		echo; echo; \
		echo export SPRING_DATASOURCE_URL="jdbc:postgresql://`$(DOCKER_COMPOSE) port db 5432`/sample"; \
		echo export SPRING_DATASOURCE_USERNAME="sampleuser"; \
		echo export SPRING_DATASOURCE_PASSWORD="samplepass";

stop:
	$(COMPOSE) $(LOCAL_CONFIG) $(COMPOSE_PGADMIN) $(COMPOSE_POSTGRES) stop
	$(COMPOSE) $(LOCAL_CONFIG) $(COMPOSE_PGADMIN) $(COMPOSE_POSTGRES) rm -f

delete:
	docker image rm $(IMAGE)

# Kubernetes via `Docker-Desktop` and `Minikube` Only supports Linux Containers.
# These `make` targets will only run with linux container builds.
# The Linux image must be available in the Minikube or Docker-Desktop Respository.
# Before running these check for the image by running `docker images` command.
# To switch to Minikube use the following command in powershell to switch the context
# . to use minikube. To return to the docker-desktop kubernetes context close and reopen
# . powershell and/or bash.

# *NIX/Bash.
# . Run command below first for minikube and make sure the image is available.
# eval $(minikube -p minikube docker-env)
kube-apply-bash:
	envsubst < ./kubernetes/kubernetes.tmpl > ./kubernetes/$(PROJECT).yml
	kubectl apply -f ./kubernetes/$(PROJECT).yml
	
kube-delete-bash:
	kubectl delete -f ./kubernetes/$(PROJECT).yml
	rm ./kubernetes/$(PROJECT).yml

# Windows powershell run.
# Need to run the .\kubernetes\envar.ps1 script to set the environment variables.
# . Run command below first for minikube and make sure the image is available.
# minikube docker-env | Invoke-Expression
kube-apply-ps:
	envsubst < ./kubernetes/kubernetes.tmpl > ./kubernetes/$(PROJECT).yml
	kubectl apply -f ./kubernetes/$(PROJECT).yml
	
kube-delete-ps:
	kubectl delete -f ./kubernetes/$(PROJECT).yml
	del .\kubernetes\$(PROJECT).yml
