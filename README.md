# fatecando-web

Warning: <b>client only</b>. Use REST API from back-end [fatecando-api](https://github.com/guto-alves/fatecando-api) project. You need to start the back-end server before starting the front-end application.

## Screenshot
<img width="1042" alt="everyone-screenshot" src="https://user-images.githubusercontent.com/48946749/109454084-42a51280-7a32-11eb-8581-a989a47e66ce.png">

## Running Fatecando locally
Fatecando is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/). You can build a jar file and run it from the command line:

```
git clone https://github.com/guto-alves/fatecando-web.git
cd everyone
./mvnw package
java -jar target/*.jar
```

You can then access fatecando here: http://localhost:8080

Or you can run it from Maven directly using the Spring Boot Maven plugin. If you do this it will pick up changes that you make in the project immediately (changes to Java source files require a compile as well - most people use an IDE for this):

```
./mvnw spring-boot:run
```

## Working with Fatecando in your IDE

Please take a look at [Working with Fatecando in Eclipse / STS - fatecando-api](https://github.com/guto-alves/fatecando-api#working-with-fatecando-in-eclipse--spring-tool-suite-sts). The prerequisites and steps are the same. 

