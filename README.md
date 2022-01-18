# Overview
This is a java and [rest-assured](https://rest-assured.io/) based test automation framework for the testing of web api's/microservices.
The build tool is [Gradle](https://gradle.org/) and the [testng](https://testng.org/doc/) test automation framework is used for specifying, arranging and running the tests. 
[Allure](https://docs.qameta.io/allure/) reporting is used to give a detailed overview of test result including steps with attached cURLs and response/request payloads. 
There's also a Jenkins set-up included to get the tests running in a pipeline.

## Setup
This will work straight out-of-the-box once your java setup is ok. Java 17 and Gradle 7.1 were used when building and testing. 
There's a gradle wrapper included, so you don't have to install gradle. Just use gradlew(or ./gradlew) rather than gradle when running commands. 
Clone the repo, open in IntelliJ, build and run the tests.

## Running Tests
You can run your tests using intelliJ straight away.
Or you can run with gradle and view the Allure report.
```
gradle test
```
If you want to view the Allure report after:
```
gradle allureServe
```
This will fire up the allure server and open the results in your default browser.

# How it Works
Several things are tied together to allow us to make requests against out api under test, serialize payloads, deserialize responses,
define our tests and report the results. Each are outlined here and how they work together to create the conditions under which the tests are run.

## Gradle
build details

## Rest Assured
requests, payloads and pojo details

## TestNG
the tests

## Allure
steps, logging requests and the final report details

## Jenkins
set up details