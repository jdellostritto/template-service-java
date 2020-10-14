.PHONY: run stop delete image package clean build
# name of the image to build
IMAGE ?= acme.io/template-service-java

DOCKER_COMPOSE ?= docker-compose
RUN_CONFIG ?= -f docker-compose.yml

COMPILE_CONFIG ?= -f docker-compose.compile.yml

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
	docker build -t $(IMAGE) .

run:
	$(COMPOSE) up -d app

stop:
	$(COMPOSE) stop
	$(COMPOSE) rm -f

delete:
	docker image rm $(IMAGE)

