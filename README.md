# Spring Boot Graphql Spqr Example With Rest Integration
> Spring Boot Graphql Api and Rest Api
>
<img src="https://github.com/susimsek/spring-boot-graphql-spqr-example/blob/main/images/spring-boot-graphql-spqr-example.png" alt="Spring Boot Graphql Spqr Example With Rest Integration" width="100%" height="100%"/> 

## Prerequisites

* Java 11
* Maven 3.3+
* Docker 19.03+
* Docker Compose 1.25+

## Installation


```sh
./mvnw compile jib:dockerBuild
```


```sh
docker-compose up -d 
```

## Installation Using Vagrant

<img src="https://github.com/susimsek/spring-boot-graphql-spqr-example/blob/main/images/vagrant-installation.png" alt="Spring Boot Vagrant Installation" width="100%" height="100%"/> 

## Prerequisites

* Vagrant 2.2+
* Virtualbox or Hyperv

```sh
vagrant up
```

```sh
vagrant ssh
```

```sh
cd vagrant/setup
```

```sh
sudo chmod u+x *.sh
```

```sh
./install-prereqs.sh
```

```sh
exit
```

```sh
vagrant ssh
```

```sh
./mvnw compile jib:dockerBuild
```

```sh
docker-compose up -d
```

> You can access the SpringDoc Openapi from the following url.

http://localhost:9090/api

> You can access the playground from the following url.

http://localhost:9090/api/playground

> You can access the voyager from the following url.

http://localhost:9090/api/voyager

> You can access the Kibana from the following url.

http://localhost:5601

## Used Technologies

* Spring Boot 2.4.3
* Elasticsearch
* Kibana
* Graphql  
* Spring Boot Graphql Spqr 
* Spring Boot Graphql
* Spring Boot Playground  
* Spring Boot Voyager
* Spring Boot Web
* Content Negotiation Support(Xml,Json,Yaml Support)
* Spring Boot Log4j2
* Problem Spring Web
* Spring Boot Actuator
* SpringDoc Openapi Web Mvc Core
* SpringDoc Openapi Web Ui
* Maven Jib Plugin
* Maven Clean Plugin
* Maven Enforcer Plugin
* Maven Compiler Plugin
* Lombok
* Mapstruct  
* Dev Tools
* Spring Boot Test

## Playground

> You can access the playground ui from the following url.

http://localhost:9090/api/playground

<img src="https://github.com/susimsek/spring-boot-graphql-spqr-example/blob/main/images/playground.png" alt="Spring Boot Playground" width="100%" height="100%"/>

## Voyager

> You can access the voyager ui from the following url.

http://localhost:9090/api/voyager

<img src="https://github.com/susimsek/spring-boot-graphql-spqr-example/blob/main/images/voyager.png" alt="Spring Boot Voyager" width="100%" height="100%"/>

## SpringDoc OpenApi

> You can access the SpringDoc Openapi from the following url.

http://localhost:9090/api

<img src="https://github.com/susimsek/spring-boot-graphql-spqr-example/blob/main/images/springdoc-openapi.png" alt="SpringDoc Openapi" width="100%" height="100%"/> 
