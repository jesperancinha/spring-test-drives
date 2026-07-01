# spring-flash-7

## Introduction

Exploring Exception Handling in Spring

Topics

1.  `@ResponseStatus(HttpStatus.NOT_FOUND)`, `@ControllerAdvice`,`ModelAndView`, `ResponseEntity`, `@ExceptionHandler`

## Endpoints

Check running on 8081

```bash
lsof -i :8081
```

1.  [http://localhost:8081/tulips](http://localhost:8081/tulips)
2.  [http://localhost:8081/tulips/ok](http://localhost:8081/tulips/ok)
3.  [http://localhost:8081/tulips/error](http://localhost:8081/tulips/error)
4.  [http://localhost:8081/flowers/carnation](http://localhost:8081/flowers/carnation)
5.  [http://localhost:8081/cars/kitt](http://localhost:8081/cars/kit)
6.  [http://localhost:8081/flowers/loca/carnation](http://localhost:8081/flowers/local/carnation)
7.  [http://localhost:8081/cars/local/kitt](http://localhost:8081/cars/local/kit)
8.  [http://localhost:8081/fourwheels/monster](http://localhost:8081/fourwheels/monster)
9.  [http://localhost:8081/pottery/amphor](http://localhost:8081/pottery/amphor)

```bash
curl http://localhost:8081/tulips
curl http://localhost:8081/tulips/ok
curl http://localhost:8081/tulips/error
curl http://localhost:8081/flowers/carnation
curl http://localhost:8081/cars/kitt
curl http://localhost:8081/flowers/local/carnation
curl http://localhost:8081/cars/local/kitt
curl http://localhost:8081/cars/local/kitt
curl http://localhost:8081/fourwheels/monster
curl http://localhost:8081/pottery/amphor
```

In order to test by keeping the session it's easier just to refresh the browser on the endpoint, but you can also use this curl command:

```bash
curl -c cookies.txt -b cookies.txt -v http://localhost:8081
```

## Resources

### Online

-   [Spring @ExceptionHandler – Multiple exceptions and global handler](https://howtodoinjava.com/spring-core/spring-exceptionhandler-annotation/)
-   [Spring Session - REST](https://docs.spring.io/spring-session/docs/current/reference/html5/guides/java-rest.html)

### Books

-   Cosmina, I. (11th December 2019). <i>Pivotal Certified Professional Core Spring 5 Developer Exam: A Study Guide Using Spring Framework 5</i>. (Second Edition). Apress
-   Sharma, R. (September 2018). <i>Hands-On Reactive Programming with Reactor</i>. (First Edition). Packt
-   Cosmina, I. Harrop, R. Schaefer, C. Ho, C. (October 2017). <i>Pro Spring 5 An In-Depth Guide to the Spring Framework and Its Tools</i>. (Fifth Edition). Apress
-   Winch, R. Mularien, P. (December 2012). <i>Spring Security 3.1</i>. (Second Edition). Packt Publishing
-   Kurniawan, B. Deck, P. (January 2015). <i>Servlet, JSP & Spring MVC</i>. (First Edition). Brainy Software
-   Long, J. (2020). <i>Reactive Spring</i>. (First Edition). Josh Long

## About me

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
