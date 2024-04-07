# spring ai app

## Requirements

For building and running the application you need:

- [JDK 1.17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html))
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. You can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

### Use the docker file in the root of the directory
run these commands

 ```shell
docker build -t spring-ai .
```

```shell
docker run -d -p 8080:8080 -e OPENAI_API_KEY="Your api key here" spring-ai
```

### Use the docker-compose file
Open the docker-compose file
Fill your api key in OPENAI_API_KEY environment variable
Now, you can run the command 

```shell
docker-compose up
```

## Execute the endpoint

You can use postman or a browser to call the endpoint

```shell
http://localhost:8082/quote
```
It will return 3 (which is the default number of quotes) popular quotes from Jean-Paul Sartre (Default author if no one else is provided)

```shell
http://localhost:8082/quote?author=Albert Einstein
```
It will provide 3 popular quotes from Albert Ensitein

```shell
http://localhost:8082/quote?author=Albert Einstein&nbQuotes=5
```
It will provide 5 popular quotes from Albert Ensitein


## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE) file.
