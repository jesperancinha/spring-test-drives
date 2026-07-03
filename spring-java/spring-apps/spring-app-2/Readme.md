# spring-app-1

📚📘📕📒📓📗📙📔📖



## Introduction

ScrapbookingForCatsAndCoffeeOwnersDotCom is a fictitious company where scrapbooks are written, made and in some cases sold!

Some scrapbooks tell stories. 📝

Some scrapbooks talk about travelling. ✈️

Some scrapbooks are about cats. 🐈

Some scrapbooks talk about personal frustrations. 😩

Some scrapbooks talk about happy life. 🥳

Some scrapbooks talk about coffee. ☕️

The goal of this application is to illustrate three fundamental principles of software development:

1.  [SOLID](https://en.wikipedia.org/wiki/SOLID) - (S)ingle responsibility, (O)pen Close, (L)iskov-substitution, (I)nterface segregation, (D)ependency inversion.
2.  [ACID](https://en.wikipedia.org/wiki/ACID) - (A)tomicity, (C)onsistency, (I)solation, (D)urability
3.  [12 Factor application](https://12factor.net/) - Codebase, Dependencies, Config, Backing Services, Build/release/run, Processes, Port Binding, Concurrency, Disposability, Dev/Prod parity, Logs, Admin/Management

## Request

```bash
curl -X POST 'http://localhost:8081/api/create' -H 'Content-type: application/json' --data '{ "name": "cats", "scrapbookType":"CATS", "pages":"100"}'
curl -X POST 'http://localhost:8081/api/create/bad' -H 'Content-type: application/json' --data '{ "name": "cats", "scrapbookType":"CATS", "pages":"100"}'

curl http://localhost:8081/api/cats
curl http://localhost:8081/api/%5Ba-zA-Z%20%21%5D

curl http://localhost:8081/api
```

## Resources

### Context

-   [Scrapbooking](https://en.wikipedia.org/wiki/Scrapbooking)

### Online

-   [What’s new in Spring Framework 5](https://developer.ibm.com/languages/java/tutorials/j-whats-new-in-spring-framework-5-theedom)
-   [Spring Framework Overview](https://docs.spring.io/spring-framework/docs/5.1.18.RELEASE/spring-framework-reference/overview.html)

### Books

-   C. Martin, R. (2018). <i>Clean Architecture - A Craftsman's Guide to Software Structure and Design</i>. (First Edition). Prentice Hall
-   C. Martin, R. (2008). <i>Clean Code - A Handbook of Agile Software Craftsmanship</i>. (First Edition). Prentice Hall
-   Cosmina, I. (11th December 2019). <i>Pivotal Certified Professional Core Spring 5 Developer Exam: A Study Guide Using Spring Framework 5</i>. (Second Edition). Apress
-   Sharma, R. (September 2018). <i>Hands-On Reactive Programming with Reactor</i>. (First Edition). Packt
-   Cosmina, I. Harrop, R. Schaefer, C. Ho, C. (October 2017). <i>Pro Spring 5 An In-Depth Guide to the Spring Framework and Its Tools</i>. (Fifth Edition). Apress
-   Winch, R. Mularien, P. (December 2012). <i>Spring Security 3.1</i>. (Second Edition). Packt Publishing
-   Kurniawan, B. Deck, P. (January 2015). <i>Servlet, JSP & Spring MVC</i>. (First Edition). Brainy Software
-   Long, J. (2020). <i>Reactive Spring</i>. (First Edition). Josh Long

## About me

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
