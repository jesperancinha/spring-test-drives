# Spring Basics Tutorial

<details>
<summary><h2><b>1. Number and Datetime Formats</b></h2></summary>

## Introduction

Exploring the

org.springframework.format.annotation.DateTimeFormat

and the

org.springframework.format.annotation.NumberFormat

annotations

Topics

1.  `org.springframework.format.annotation.DateTimeFormat`
2.  `org.springframework.format.annotation.NumberFormat`

## Endpoints

1.  Date Example

```bash
curl -X POST http://localhost:8081/time --header 'currentTime: 2014-11-01' --header 'Content-Type: application/text'
```

2.  Datetime Example

```bash
curl -X POST http://localhost:8081/time/time --header 'currentTime: 2014-12-12 10:00:00' --header 'Content-Type: application/text'
```

3.  Number Example

```bash
curl -X POST http://localhost:8081/time/dollars --header 'dollars: $1000000' --header 'Content-Type: application/text'
```
</details>


<details>
<summary><h2><b>2. Sequence generator</b></h2></summary>

## Introduction

Exploring how to make sequence generators

Topics

1.  `@SequenceGenerator`

## Endpoints

1.  Save all potatoes!

```bash
curl -X POST  http://localhost:8081/potatoes
```

2.  Get all potatoes! Notice the start and the loop

```bash
curl http://localhost:8081/potatoes
```
</details>


<details>
<summary><h2><b>3. Spring session</b></h2></summary>

## Introduction

Exploring how to use Spring Session

Topics

## Endpoints

Check running on 8081

```bash
lsof -i :8081
```

1.  http://localhost:8081/session

```bash
curl http://localhost:8081/session
```

To test by keeping the session, it's easier just to refresh the browser on the endpoint, but you can also use this curl command:

```bash
curl -c cookies.txt -b cookies.txt -v http://localhost:8081/session
```
</details>

<details>
<summary><h2><b>4. Exception Handling</b></h2></summary>

## Introduction

Exception Handling in Spring

Topics

1.  `@ResponseStatus(HttpStatus.NOT_FOUND)`, `@ControllerAdvice`,`ModelAndView`, `ResponseEntity`, `@ExceptionHandler`

## Endpoints

Check running on 8081

```bash
lsof -i :8081
```

1.  [http://localhost:8081/products/tulips](http://localhost:8081/tulips)
2.  [http://localhost:8081/products/tulips/ok](http://localhost:8081/tulips/ok)
3.  [http://localhost:8081/products/tulips/error](http://localhost:8081/tulips/error)
4.  [http://localhost:8081/products/flowers/carnation](http://localhost:8081/flowers/carnation)
5.  [http://localhost:8081/products/cars/kitt](http://localhost:8081/cars/kit)
6.  [http://localhost:8081/products/flowers/loca/carnation](http://localhost:8081/flowers/local/carnation)
7.  [http://localhost:8081/products/cars/local/kitt](http://localhost:8081/cars/local/kit)
8.  [http://localhost:8081/products/fourwheels/monster](http://localhost:8081/fourwheels/monster)
9.  [http://localhost:8081/products/pottery/amphor](http://localhost:8081/pottery/amphor)

```bash
curl http://localhost:8081/products/tulips
curl http://localhost:8081/products/tulips/ok
curl http://localhost:8081/products/tulips/error
curl http://localhost:8081/products/flowers/carnation
curl http://localhost:8081/products/cars/kitt
curl http://localhost:8081/products/flowers/local/carnation
curl http://localhost:8081/products/cars/local/kitt
curl http://localhost:8081/products/cars/local/kitt
curl http://localhost:8081/products/fourwheels/monster
curl http://localhost:8081/products/pottery/amphor
```

In order to test by keeping the session it's easier just to refresh the browser on the endpoint, but you can also use this curl command:

```bash
curl -c cookies.txt -b cookies.txt -v http://localhost:8081/products
```

</details>

<details>
<summary><h2><b>5. Custom Health Endpoints</b></h2></summary>

## Introduction

Exploring Health Indicator in Spring

Topics

1.  `FileHealthIndicator`, `AbstractHealthIndicator`, `health`, `doHealthCheck`

## Endpoints

1.  [http://localhost:8081/actuator/health](http://localhost:8081/actuator/health)

```bash
curl localhost:8081/actuator/health
```

## How to run

Copy file [testfile.txt](testfile.txt) to [/tmp](/tmp)

```bash
cp testfile.txt /tmp
```

Run Spring Boot

1.  Running Health Checks the old way

```bash
mvn clean install spring-boot:run -Dspring-boot.run.profiles=test
```

2.  Running Health Checks the new way

```bash
mvn clean install spring-boot:run -Dspring-boot.run.profiles=prod
```

</details>

<details>
<summary><h2><b>6. Spring Interceptors</b></h2></summary>

## Introduction

Exploring Interceptors in Spring using annotations

Topics

1.  `WebMvcConfigurer`, `InterceptorRegistry`, `addInterceptors`

## Endpoints

1.  [http://localhost:8081/wine](http://localhost:8081/wine)

```bash
curl http://localhost:8081/wine
```

## How to run

1.  Test running services

```bash
lsof -i :8081
```

2.  Run service

```bash
mvn clean install spring-boot:run
```

</details>

<details>
<summary><h2><b>7. ConfigurationProperties</b></h2></summary>

## Introduction

Exploring ConfigurationProperties in Spring

Topics

1.  `ConfigurationProperties`, `@Component`, `@PropertySource`

## Endpoints

1.  [http://localhost:8081/lyrics](http://localhost:8081/lyrics)

```bash
curl localhost:8081/lyrics
```

## How to run

1.  Test running services

```bash
lsof -i :8081
```

2.  Run service

```bash
mvn clean install spring-boot:run
```

</details>


## Resources

### Books

-   Cosmina, I. (11th December 2019). <i>Pivotal Certified Professional Core Spring 5 Developer Exam: A Study Guide Using Spring Framework 5</i>. (Second Edition). Apress
-   Sharma, R. (September 2018). <i>Hands-On Reactive Programming with Reactor</i>. (First Edition). Packt
-   Cosmina, I. Harrop, R. Schaefer, C. Ho, C. (October 2017). <i>Pro Spring 5 An In-Depth Guide to the Spring Framework and Its Tools</i>. (Fifth Edition). Apress
-   Winch, R. Mularien, P. (December 2012). <i>Spring Security 3.1</i>. (Second Edition). Packt Publishing
-   Kurniawan, B. Deck, P. (January 2015). <i>Servlet, JSP & Spring MVC</i>. (First Edition). Brainy Software
-   Long, J. (2020). <i>Reactive Spring</i>. (First Edition). Josh Long

## About me

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
