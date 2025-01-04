# spring-boot-grpc-client-server
Project demonstrating Springboot 3.4.1 with Java 19 and gRPC protocol using client server

The goal of this project is to implement two [`Spring Boot`](https://docs.spring.io/spring-boot/index.html) applications using [`gRPC`](https://grpc.io/): the server, called `movie-server`, and the shell client, named `movie-client`. The library `movie-lib` defines the `gRPC` interface that both the server and client applications use.

## Applications

- **movie-lib**

  A Maven project that defines the `gRPC` interface (using [`Protocol Buffers`](https://protobuf.dev/)) for managing movies. This library is shared by both the `movie-server` and `movie-client` to ensure they can communicate properly over `gRPC`.

- **movie-server**

  A Spring Boot web application that has `movie-lib` as dependency. It implements the `gRPC` functions for managing movies and runs a `gRPC` server to handle `movie-client` calls. The movies are stored in a [`PostgreSQL`](https://www.postgresql.org/) database.

- **movie-client**

  A Spring Boot shell application that has `movie-lib` as dependency. It has a `stub` used to call `movie-server` functions.

## Install gRPC compiler
  Download compiler from [`proto buf zip download`](https://github.com/protocolbuffers/protobuf/tree/main/java) according to your os, for example [`protoc-29.2-win64.zip`](https://github.com/protocolbuffers/protobuf/releases/tag/v29.2) 
  Put the exe file in path, and try 
```
protoc --version
```
## and then in lib folder do 

```
protoc --java_out=${OUTPUT_DIR} path/to/your/proto/file
```
## This will build and generate MovieProto.java

## Packaging and Installing movie-grpc-lib

In a terminal and inside the `spring-boot-grpc-client-server` root folder, run the command below:
```
mvn clean install --projects movie-lib
```
## Do an mvn clean install on the root folder

## Start PostgreSQL Docker container using cmd, It will  download image and start container. Docker needs to be pre-installed

Run the command below to start `postgres` Docker container
```
docker run -d --name postgres \
  -p 5432:5432 \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -e POSTGRES_DB=moviesdb \
  postgres:17.2
```

## Running applications

- **movie-server**

  In a terminal and inside the `spring-boot-grpc-client-server` root folder, run the following command:
  ```
  mvn clean spring-boot:run --projects movie-server
  ```
  or you can run main class by right clicking in IDE

- **movie-client**

  Open another terminal, make sure you are in the `spring-boot-grpc-client-server` root folder. Then, run the command below to build the executable jar file:
  ```
  mvn clean package --projects movie-client
  ```

  Finally, to start the client shell, run:
  ```
  ./movie-client/target/movie-client-0.0.1-SNAPSHOT.jar
  ```
  Or you can directly run main class in client.

## Demo

## Shutdown

- To stop the applications, go to the terminals where they are running and press `Ctrl+C`;
- To stop the `postgres` Docker container, run:
  ```
  docker rm -fv postgres
  ```