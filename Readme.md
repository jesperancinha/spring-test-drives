# Spring Test Drives

---


[![Generic badge](https://img.shields.io/static/v1.svg?label=GitHub&message=Spring%20Test%20Drives&color=informational)](https://github.com/jesperancinha/spring-test-drives)
[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

[![CircleCI](https://circleci.com/gh/jesperancinha/spring-test-drives.svg?style=svg)](https://circleci.com/gh/jesperancinha/spring-test-drives)
[![Build status](https://ci.appveyor.com/api/projects/status/wksvhmqaq0sd8505?svg=true)](https://ci.appveyor.com/project/jesperancinha/spring-test-drives)
[![spring-test-drives](https://github.com/jesperancinha/spring-test-drives/actions/workflows/spring-test-drives.yml/badge.svg)](https://github.com/jesperancinha/spring-test-drives/actions/workflows/spring-test-drives.yml)

[![Codacy Badge](https://app.codacy.com/project/badge/Grade/9d14f60a58bd456fb1084860b5a46871)](https://www.codacy.com/gh/jesperancinha/spring-test-drives/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jesperancinha/spring-test-drives&amp;utm_campaign=Badge_Grade)
[![codebeat badge](https://codebeat.co/badges/b9097b8c-40f8-48bf-beb3-2007803b4bad)](https://codebeat.co/projects/github-com-jesperancinha-spring-test-drives-master)
[![Known Vulnerabilities](https://snyk.io/test/github/jesperancinha/spring-test-drives/badge.svg)](https://snyk.io/test/github/jesperancinha/spring-test-drives)

[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/9d14f60a58bd456fb1084860b5a46871)](https://www.codacy.com/gh/jesperancinha/spring-test-drives/dashboard?utm_source=github.com&utm_medium=referral&utm_content=jesperancinha/spring-test-drives&utm_campaign=Badge_Coverage)
[![Coverage Status](https://coveralls.io/repos/github/jesperancinha/spring-test-drives/badge.svg?branch=master)](https://coveralls.io/github/jesperancinha/spring-test-drives?branch=master)
[![codecov](https://codecov.io/gh/jesperancinha/spring-test-drives/branch/master/graph/badge.svg?token=08TJV16VKG)](https://codecov.io/gh/jesperancinha/spring-test-drives)

[![GitHub language count](https://img.shields.io/github/languages/count/jesperancinha/spring-test-drives.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/top/jesperancinha/spring-test-drives.svg)](#)
[![GitHub top language](https://img.shields.io/github/languages/code-size/jesperancinha/spring-test-drives.svg)](#)

## Technologies used

Please check the [TechStack.md](TechStack.md) file for details.

## Description

SPRING study project.

The purpose of this project is to keep up with the development of the Spring Framework

See [Log Book](./LogBook.md) for further details.

#### Stable releases

-   [0.0.0](https://github.com/jesperancinha/spring-test-drives/tree/0.0.0) - [d8700ab7d597fb825cbfc1fca30c2153ef5141ea](https://github.com/jesperancinha/spring-test-drives/tree/0.0.0) - Spring boot 2.7.0 / JDK 11
-   [1.0.0](https://github.com/jesperancinha/spring-test-drives/tree/1.0.0) - [395d79e218595e57365c7ad9723c2aa20ad77881](https://github.com/jesperancinha/spring-test-drives/tree/1.0.0) - Spring boot 2.7.5 / JDK 17
-   [2.0.0](https://github.com/jesperancinha/spring-test-drives/tree/2.0.0) - [179ec86df1033b9d551621173751bd60590da775](https://github.com/jesperancinha/spring-test-drives/tree/2.0.0) - Spring boot 3.0.0 / JDK 17
-   [3.0.0](https://github.com/jesperancinha/spring-test-drives/tree/3.0.0) - [49d0d3863ff7079c473b2a8bfe285c7735a71f9c](https://github.com/jesperancinha/spring-test-drives/tree/3.0.0) - Spring boot 3.0.2 / JDK 19

## Contents

1.  [Spring](./spring-5) - Test drives for the Spring 5 Frawework (Bare minimum focus given to Spring Boot)
2.  [Spring Kotlin](./spring-kotlin-5) - Test drives for the Spring 5 Frawework in Kotlin (Bare minimum focus given to Spring Boot)
3.  [Spring XML based services](./xml-based-services) - A collection of old services still based one way or the other on xml. 

## Specs

1.  Spring: [org.springframework](https://mvnrepository.com/artifact/org.springframework/) - Latest version
2.  Spring Boot: [org.springframework.boot/spring-boot-starter-parent](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-parent) - Latest version

## Build

Note that if you want to build this project from the root, you need to use one of the JDK 11 or upper versions.

<i>See [Hints&Tricks](https://github.com/jesperancinha/project-signer/blob/master/project-signer-templates/Hints%26Tricks.md)
document for more details</i>
```bash
mvn clean install -Dconsolerizer.show=false
```

## Install JDK using [SDK-MAN](https://sdkman.io/)

1.  List all Java versions and select the appropriate on currently used in the project. Check the [pom.xml](pom.xml) file for details.
```shell
sdk list java
```

2.  Install the java version

```shell
sdk install java <JDK_VERSION>
```

3.  Use the JDK version

```shell
sdk use java <JDK_VERSION>
```

## Detect Non-Running Tests

```bash
mvn clean install | egrep "Tests run|----"
```
## Sequence Diagram

```mermaid

sequenceDiagram
    participant USER
    participant SPRING
    participant Spring Professional
    
    rect rgb(1,130,25)
    
    USER->>SPRING: User studies spring
    
    SPRING->>Spring Professional: User becomes
 
    loop Keep updated
         Spring Professional->>SPRING: User studies again
    end
    end
```

<i>Note: You need a Mermaid plugin extension</i>

i.e. [mermaid-plugin](https://chrome.google.com/webstore/detail/mermaid-diagrams/phfcghedmopjadpojhmmaffjmfiakfil/related)

## Tech-stack Cloud

`@Transational timeout`, `TestRestTemplate`, `HealthIndicator`, `actuator`, `Embedded`, `JPA`, `Big Data`

## Running unit and integration tests

The examples presented in this repo need to use a docker-engine/docker-machine in order to run.

This is because they make use of the [testcontainers](https://www.testcontainers.org/) project.

I tested this using Docker Desktop for Mac and on an ubuntu-unity machine. As long as the docker-machine/docker-engine is running, the maven build should also work.

Using embedded alternatives revealed to be quite cumbersome, inefficient and slow.

For more info on docker desktop, please check their [website](https://www.docker.com/).

To learn how to install docker in ubuntu, please check their [website](https://docs.docker.com/engine/install/ubuntu/).

When this is ready, just run:

```shell
make build
```

or

```shell
mvn clean install
```

## Resources

### Online

-   [How to test the REST Clients of your Spring Boot Application with @RestClientTest](https://jschmitz.dev/posts/how_to_test_the_rest_clients_of_your_spring_boot_application_with_restclienttest/)
-   [Introduction to OpenRewrite](https://docs.openrewrite.org/)

### Books

-   Cosmina, I. (11th December 2019). <i>Pivotal Certified Professional Core Spring 5 Developer Exam: A Study Guide Using Spring Framework 5</i>. (Second Edition). Apress
-   Sharma, R. (September 2018). <i>Hands-On Reactive Programming with Reactor</i>. (First Edition). Packt
-   Cosmina, I. Harrop, R. Schaefer, C. Ho, C. (October 2017). <i>Pro Spring 5 An In-Depth Guide to the Spring Framework and Its Tools</i>. (Fifth Edition). Apress
-   Winch, R. Mularien, P. (December 2012). <i>Spring Security 3.1</i>. (Second Edition). Packt Publishing
-   Kurniawan, B. Deck, P. (January 2015). <i>Servlet, JSP & Spring MVC</i>. (First Edition). Brainy Software
-   Long, J. (2020). <i>Reactive Spring</i>. (First Edition). Josh Long

### Online

-   [What’s new in Spring Framework 5](https://developer.ibm.com/languages/java/tutorials/j-whats-new-in-spring-framework-5-theedom)
-   [Spring Framework Overview](https://docs.spring.io/spring-framework/docs/5.1.18.RELEASE/spring-framework-reference/overview.html)
-   [Spring Framework Documentation - Current Version](https://docs.spring.io/spring-framework/docs/current/reference/html/index.html)

## About me

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
