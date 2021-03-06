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
**Plugins**

Applying the following plugins to the project allows us to extend the project’s capabilities.
```
plugins {
    id 'java'
    id 'io.qameta.allure' version '2.9.6'
    id 'io.freefair.lombok' version '6.3.0'
}
```
1. The java plugin adds Java compilation along with testing and bundling capabilities to the project.
2. The allure plugin is for building Allure reports for TestNG(or other test frameworks such as JUnit or Spock).
3. The lombok plugin is for annotation processing and compiling.


**Compatibility Options**

```
sourceCompatibility = JavaVersion.VERSION_17
targetCompatibility = JavaVersion.VERSION_17
```

* sourceCompatibility is Java version compatibility to use when compiling Java source.
* targetCompatibility is Java version to generate classes for.


**Repositories**

The repositories for which our project resolves its dependencies.
```
repositories {
    mavenCentral()
    google()
}
```
See the [gradle documentation](https://docs.gradle.org/current/userguide/declaring_repositories.html) for more information around other repositories 
and setting up custom repositories.


**Dependencies**

The things that supports in building our project.
```
dependencies {
    implementation(
            'io.rest-assured:rest-assured:4.4.0',
            'com.fasterxml.jackson.core:jackson-databind:2.13.1',
            'com.fasterxml.jackson.core:jackson-core:2.13.1',
            'com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.1',
            'org.hamcrest:hamcrest:2.2',
            'io.qameta.allure:allure-testng:2.17.2',
            'io.qameta.allure:allure-rest-assured:2.17.2'
    )
    testImplementation(
            'org.testng:testng:7.5'
    )
}
```
1. rest-assured: for all rest-assured commands.
2. jackson: for serialisation and deserialization.
3. hamcrest: for the hamcrest matchers used in our assertions.
4. allure: allure report and testng configuration.
5. testng: for use of testng framework as our test runner.


**Allure**

For configuring allure. Here we are only pass the version parameter but there are many parameters for configuration.
```
allure {
    version = '2.17.2'
}
```

**Test**

The task to run our tests and specifying the test configuration.

```
test {
    systemProperties System.getProperties()
    systemProperties.remove("java.endorsed.dirs")
    scanForTestClasses = false
    useTestNG() {
        useDefaultListeners = true
    }
    testLogging.showStandardStreams = true
}
```


## Rest Assured
BaseApi.java specifies how are the requests are made using Rest-Assureds RequestSpecification.
```
RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setContentType(ContentType.JSON)
            .addFilter(new AllureRestAssured())
            .build();
```

Here we specify the BASE_URL to be used by all our requests. This is a system variable that can be passed in when we build the project, so we can run on any environment.
```
./gradlew test -DbaseUrl=http://www.MY_URL.com/
```
Our content type is always set to JSON. And we add a filter that enables requests/responses to be logged in our allure report.
We then create a RequestSpecification object and say we'll use the RequestSpecification outlined above thus alleviating the need for rest-assured given() method to be littered throughout our tests.
```
protected RequestSpecification requestSender = RestAssured.given(requestSpecification);
```
I try to avoid using the given(), when() and then rest assured methods in my tests as I don't think it's readable. Every api class that we create to make requests with now extent this so our code will be much cleaner.
```
public Response postBooking(Booking payload) {
   return requestSender.body(payload)
                       .post(PATH);
}
```

See RequestSpecification documentation for more details.

The payloads for requests are POJOs. As are our request responses. 
```
@JsonProperty
private int roomid;
```
This is where jackson is used to serialize/deserialize respectively. Ensure the variable name matches that which is required in the payload or seen in the api response.
There are ways to rename to what you wish. To deserialize we use the rest-assured 'as' function and pass in the desired POJO class.
```
BookingResponse createdBookingResponse = bookingApi.postBooking(payload).as(BookingResponse.class);
```
This allows us to work with the java defined in the class. Typically, the getters. In the response POJOs note the use of the lombok '@Getter' annotation.
This is used, so we don't have to write getter boilerplate code. 'createdBookingResponse.' and you'll see all the getter.

## TestNG
The tests live in the test module. Every class of tests will extend the BaseTest.java class which will house items common to all.
TestNG is the test runner. Simply create a method in one of the test classes and annotate with @Test the TestNG library.
```
@Test(description = "get bookings 200s")
public void getBookingShouldReturn200(){
   Response response = bookingApi.getBookings();

   assertThat("Incorrect response code", response.getStatusCode(), is(200));
}
```
Hamcrest matchers and assertions are then used to compare actual and expected results.

## Allure
Each request is annotated, so we'll have a readable description in our final allure report.
```
@Step("get booking request")
```
As mentioned, the allure filter is added to the request spec in BaseApi.java. Now every rest-assured request, response and cURL will appear under the step.
```
.addFilter(new AllureRestAssured())
```
To view the final report run 'gradle allureServe'

## Jenkins
set up details