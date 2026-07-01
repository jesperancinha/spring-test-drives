# spring-flash-2-14

## Introduction

Exploring Database transactions in Spring

Topics

1.  `Isolation.READ_UNCOMMITTED`, `Isolation.READ_COMMITTED`, `Isolation.REPEATABLE_READ`, `Isolation.SERIALIZABLE`

## Endpoints

```bash
curl -X POST http://localhost:8081/create/uncommitted --header "Content-Type: application/json"  --data '{"model":"Phantom II", "brand":"Rolls-Royce", "year":"1929", "movieAppearances":["Indiana Jones And The Last Cruzade","The Sorcerer''s Apprentice"]}'
curl -X POST http://localhost:8081/create/committed --header "Content-Type: application/json"  --data '{"model":"Firebird Trans Am", "brand":"Pontiac", "year":"1982", "movieAppearances":["Knight Rider", "MCQ", "The Hunter"]}'
curl -X POST http://localhost:8081/create/repeatable --header "Content-Type: application/json"  --data '{"model":"Firebird Trans Am", "brand":"Pontiac", "year":"1982", "movieAppearances":["Knight Rider", "MCQ", "The Hunter"]}'
curl -X POST http://localhost:8081/create/serializable --header "Content-Type: application/json"  --data '{"model":"Phantom II", "brand":"Rolls-Royce", "year":"1929", "movieAppearances":["Indiana Jones And The Last Cruzade","The Sorcerer''s Apprentice"]}'
```

## How to run

### Setting up database (live installation)

-   MAC-OS installation

```bash
brew install mariadb;
```

-   Database setup

```sql
CREATE database db;
SHOW DATABASES;
CREATE USER sa@localhost IDENTIFIED BY 'sa';
GRANT ALL PRIVILEGES ON *.* TO 'sa'@localhost IDENTIFIED BY 'sa';
FLUSH PRIVILEGES;
SHOW GRANTS for sa@localhost;
SELECT @@GLOBAL.tx_isolation, @@tx_isolation;
SET GLOBAL TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;
SET SESSION tx_isolation='READ-UNCOMMITTED';
SELECT @@GLOBAL.tx_isolation, @@tx_isolation;
```

#### Maria DB Socket issue

> ERROR 2002 (HY000): Can't connect to local MySQL server through socket '/tmp/mysql.sock' (2 "No such file or directory")

```bash
brew remove mysql
brew remove mariadb
brew cleanup
sudo rm -rf /usr/local/var/mysql
```

A more extended solution: [Homebrew MariaDB/MySQL socket issues](https://laracasts.com/discuss/channels/servers/homebrew-mariadbmysql-socket-issues).

-   Configuration

```sql
SELECT * FROM INFORMATION_SCHEMA.GLOBAL_VARIABLES WHERE VARIABLE_VALUE LIKE '%\/%';
SHOW GLOBAL VARIABLES LIKE 'PORT';
```

### Dirty Read Example

Via spring annotations, it seems difficult to get dirty reads to work.

1.  After setting MariaDB on your machine, run spring with:

```bash
mvn clean install spring-boot:run -Dspring-boot.run.profiles=prod
```

2.  Then run:

```bash
mysql
```

3.  At the command mysql> prompt:

Transaction 1:

```sql
START TRANSACTION;
INSERT INTO car (id, model, brand, year, movie_appearances) VALUES (1,"Phantom II", "Rolls-royce", 1929,"Indiana Jones And The Last Cruzade");

-- Run other transaction here

ROLLBACK;
COMMIT;
```

4.  At another command mysql> prompt:
   Transaction 2:

```sql
SELECT * from car;
```

If you run rollback and then run the select again, you'll notice that you can always read. This is what dirty read is about. Reading all the transaction progress without performing a commit from another transaction.

### Regular tests

1.  Test running services

```bash
lsof -i :8081
```

2 Run service with test profile. We'll see the CSRF Filter in the logs

```bash
mvn clean install spring-boot:run
```

3.  Test cases

```bash
curl -X POST http://localhost:8081/create/uncommitted --header "Content-Type: application/json"  --data '{"model":"Phantom II", "brand":"Rolls-Royce", "year":"1929", "movieAppearances":["Indiana Jones And The Last Cruzade","The Sorcerer''s Apprentice"]}'
curl -X POST http://localhost:8081/create/committed --header "Content-Type: application/json"  --data '{"model":"Firebird Trans Am", "brand":"Pontiac", "year":"1982", "movieAppearances":["Knight Rider", "MCQ", "The Hunter"]}'
curl -X POST http://localhost:8081/create/repeatable --header "Content-Type: application/json"  --data '{"model":"Firebird Trans Am", "brand":"Pontiac", "year":"1982", "movieAppearances":["Knight Rider", "MCQ", "The Hunter"]}'
curl -X POST http://localhost:8081/create/serializable --header "Content-Type: application/json"  --data '{"model":"Phantom II", "brand":"Rolls-Royce", "year":"1929", "movieAppearances":["Indiana Jones And The Last Cruzade","The Sorcerer''s Apprentice"]}'
```

## Resources

### Online

-   [Homebrew MariaDB/MySQL socket issues](https://laracasts.com/discuss/channels/servers/homebrew-mariadbmysql-socket-issues)
-   [Installing MariaDB Server on macOS Using Homebrew](https://mariadb.com/kb/en/installing-mariadb-on-macos-using-homebrew/)
-   [Configuring Spring Boot for MariaDB](https://springframework.guru/configuring-spring-boot-for-mariadb/)
-   [Resetting the MySQL root password](https://www.a2hosting.com/kb/developer-corner/mysql/reset-mysql-root-password)
-   [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
-   [Spring Framework - Converter Examples](https://www.logicbig.com/how-to/code-snippets/jcode-spring-framework-converter.html)
-   [Spring transaction isolation level tutorial](https://www.byteslounge.com/tutorials/spring-transaction-isolation-tutorial)
-   [Transaction Isolation Levels (ODBC)](https://docs.microsoft.com/en-us/sql/odbc/reference/develop-app/transaction-isolation-levels?view=sql-server-ver15)
-   [A beginner’s guide to Phantom Read anomaly](https://vladmihalcea.com/phantom-read/)
-   [Spring Boot @DataJpaTest tutorial](https://zetcode.com/springboot/datajpatest/)
-   [Spring boot log4j2.xml example](https://howtodoinjava.com/spring-boot2/logging/spring-boot-log4j2-config/)
-   [27. Logging](https://docs.spring.io/spring-boot/docs/2.1.18.RELEASE/reference/html/boot-features-logging.html)
-   [Chapter 3: Logback configuration](http://logback.qos.ch/manual/configuration.html)
-   [SPRING BOOT AUTO CONFIGURATION](https://jaxlondon.com/blog/spring-boot-auto-configuration/)
-   [Custom Starter with Spring Boot](https://www.javadevjournal.com/spring-boot/spring-boot-custom-starter/)
-   [@PreAuthorize and @PostAuthorize in Spring Security](https://www.concretepage.com/spring/spring-security/preauthorize-postauthorize-in-spring-security)
-   [9.5. Security Filters](https://docs.spring.io/spring-security/site/docs/5.3.3.BUILD-SNAPSHOT/reference/html5/#servlet-security-filters)
-   [Spring Security Filters Chain](https://www.javadevjournal.com/spring-security/spring-security-filters/)
-   [Spring JDBC - ResultSetExtractor Interface](https://www.tutorialspoint.com/springjdbc/springjdbc_resultsetextractor.htm)
-   [Accessing Relational Data using JDBC with Spring](https://spring.io/guides/gs/relational-data-access/)
-   [Spring MVC Interceptor Example – XML and Annotation Java Config](https://howtodoinjava.com/spring-core/spring-mvc-interceptor-example/)
-   [Custom Container Configuration in Spring Boot 2](https://www.javaprogramto.com/2020/04/spring-boot-embeddedservletcontainercustomizer-configurableembeddedservletcontainer.html)
-   [Spring 4 REST + CORS Integration using @CrossOrigin Annotation + XML + Filter Example](https://www.concretepage.com/spring-4/spring-4-rest-cors-integration-using-crossorigin-annotation-xml-filter-example)
-   [How Does Spring @Transactional Really Work?](https://dzone.com/articles/how-does-spring-transactional)
-   [Spring aop aspectJ pointcut expression examples](https://howtodoinjava.com/spring-aop/aspectj-pointcut-expressions/)
-   [Spring Boot AOP After Throwing Advice](https://www.javatpoint.com/spring-boot-aop-after-throwing-advice#:~:text=After%20throwing%20is%20an%20advice,implement%20the%20after%20throwing%20advice.)
-   [Part 5: Integrating Spring Security with Spring Boot Web](https://spr.com/part-5-integrating-spring-security-with-spring-boot-web/)
-   [Spring Security – JdbcUserDetailsManager Example | JDBC Authentication and Authorization](https://www.javainterviewpoint.com/spring-security-jdbcuserdetailsmanager-example/)
-   [Spring Security - Understanding AuthenticationProvider and creating a custom one](https://www.logicbig.com/tutorials/spring-framework/spring-security/custom-authentication-provider.html)
-   [Creating a Custom Login Form](https://docs.spring.io/spring-security/site/docs/4.2.20.RELEASE/guides/html5/form-javaconfig.html#obtaining-the-sample-project)
-   [How to use Custom DAO class in Spring Security for authentication and authorization](http://www.javaroots.com/2013/03/how-to-use-custom-dao-classe-in-spring.html)
-   [Spring Boot with H2 Database](https://howtodoinjava.com/spring-boot2/h2-database-example/)
-   [Spring Security: Authentication and Authorization In-Depth](https://www.marcobehler.com/guides/spring-security)
-   [Spring Boot @ConfigurationProperties example](https://mkyong.com/spring-boot/spring-boot-configurationproperties-example/)
-   [Spring Boot custom HealthIndicator](https://blog.jayway.com/2014/07/22/spring-boot-custom-healthindicator/)
-   [Spring Boot HealthIndicator by Example](https://stackoverflow.com/questions/47935369/spring-boot-healthindicator-by-example)
-   [Spring Boot static resource processing](https://www.programmersought.com/article/2664508486/)
-   [Spring form tag : ModelAttribute VS CommandName](http://mwakram.blogspot.com/2014/05/spring-form-tag-modelattribute-vs.html)
-   [Spring @RequestMapping Annotation Examples](https://howtodoinjava.com/spring-mvc/spring-mvc-requestmapping-annotation-examples/)
-   [Exception Handling in Spring MVC](https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc)
-   [Streaming Text Oriented Messaging Protocol](https://en.wikipedia.org/wiki/Streaming_Text_Oriented_Messaging_Protocol)
-   [Using STOMP JS](https://stomp-js.github.io/stomp-websocket/codo/extra/docs-src/Usage.md.html)
-   [Spring Boot WebSocket STOMP SockJS Example](https://www.javaguides.net/2019/06/spring-boot-websocket-stomp-sockjs-example.html)
-   [STOMP Protocol Specification, Version 1.2](https://stomp.github.io/stomp-specification-1.2.html#Abstract)
-   [26. WebSocket Support](https://docs.spring.io/spring-framework/docs/4.3.x/spring-framework-reference/html/websocket.html)
-   [Spring Boot JSP View Resolver Example](https://howtodoinjava.com/spring-boot/spring-boot-jsp-view-example/)
-   [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
-   [Creating a Web Application with Spring Boot with JSP](https://www.springboottutorial.com/creating-web-application-with-spring-boot)
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
