# spring-mastery-1 - Spring Mastery 1

## Intro

This module previously relied on the long-archived `org.springframework.security.oauth:spring-security-oauth2`
library (`@EnableAuthorizationServer`/`AuthorizationServerConfigurerAdapter`, `@EnableResourceServer`/
`ResourceServerConfigurerAdapter`, `ClientDetailsServiceConfigurer`, `TokenStore`), which in turn depended on
the equally long-removed `WebSecurityConfigurerAdapter`, so the module could no longer even start.

It has since been migrated to current Spring Security OAuth2 support: Spring Authorization Server, now bundled
directly into Spring Security (`spring-security-oauth2-authorization-server`, via the
`spring-boot-starter-security-oauth2-authorization-server` starter) for the Authorization Server side, and
`spring-boot-starter-security-oauth2-resource-server` for the Resource Server side. See
`oauth/config/Mastery1AuthorizationServerConfigurer`, `oauth/config/Mastery1ResourceServerConfigurer` and
`oauth/config/Mastery1TokenStoreConfig` for the new setup:

-   `Mastery1AuthorizationServerConfigurer` registers a `RegisteredClientRepository` (client id/secret,
    grant types, scopes) and an `authorizationServerSecurityFilterChain` (`@Order(1)`) that handles the
    `/oauth2/authorize`, `/oauth2/token` and `/oauth2/jwks` protocol endpoints. Note that the
    resource-owner-password-credentials grant (`password`) the old configuration exposed is gone -
    Spring Authorization Server doesn't support it, per the OAuth 2.1 recommendation to drop it;
    `client_credentials` is registered instead for straightforward machine-to-machine testing, alongside
    `authorization_code`/`refresh_token`.
-   `Mastery1TokenStoreConfig` generates an in-memory RSA key pair and exposes it as a `JWKSource`/
    `JwtDecoder`, replacing the old JDBC-backed `TokenStore`/`DefaultTokenServices` - tokens are now
    self-contained, signed JWTs rather than opaque tokens persisted in a database.
-   `Mastery1ResourceServerConfigurer` exposes the `defaultSecurityFilterChain` (`@Order(2)`) that
    validates those JWTs via `HttpSecurity.oauth2ResourceServer(oauth2 -> oauth2.jwt(...))` for every
    other request.

## Description

French language in music

Topics covered:

1.  `@ResponseBody`, `@RequestMapping`, `@RequestParam`
2.  `AuthenticationProvider`, `RegisteredClientRepository`, `HttpSecurity.oauth2AuthorizationServer(...)`, `HttpSecurity.oauth2ResourceServer(...)`, `JWKSource`, `JwtDecoder`
3.  `@DataJpaTest`, `@ExtendWith(SpringExtension.class)`
4.  `InMemoryAuditEventRepository`, `AbstractAuditListener`, `management.endpoints.web.exposure.include=*`
5.  `@EnableConfigurationProperties`, `@ConfigurationProperties(prefix = "mastery1")`, `@PropertySource("classpath:extras.properties")`
6.  `propagation = Propagation.REQUIRES_NEW`, `isolation = Isolation.SERIALIZABLE`, `rollbackFor = RuntimeException.class`, `rollbackForClassName = "RuntimeException"`, `noRollbackForClassName = "Error"`, `noRollbackFor = Error.class`
7.  `@Qualifier`, `@DataJpaTest`
8.  `@Query("select m from Member m where m.joinDate is not null")`
9.  `TransactionTemplate`, `PlatformTransactionManager`, `TransactionStatus`
10. `@Transactional`, `JDK proxies`, `EntityManager`, `getReference`

## Endpoints

1.  [http://localhost:8081/actuator](http://localhost:8081/actuator)
2.  [http://localhost:8081/member/search?param=Celine](http://localhost:8081/member/search?param=Celine)
3.  [http://localhost:8081/member](http://localhost:8081/member)
4.  [http://localhost:8081/member/nonnull/](http://localhost:8081/member/nonnull/)

## Command line requests

```bash
curl -X DELETE http://localhost:8081/member/delete/1
curl http://localhost:8081/actuator
curl http://localhost:8081/member
curl http://localhost:8081/member/search?param=Celine
curl http://localhost:8081/member/nonnull/
curl -X POST -H 'Content-Type: application/json' http://localhost:8081/member/create --data '{"name":"Françoise Hardy","joinDate":"1960-01-01"}'
curl -X POST -H 'Content-Type: application/json' http://localhost:8081/member/create/rollback --data '{"name":"Françoise Hardy","joinDate":"1960-01-01"}'
```

## How to run

1.  Running with the default profile

```bash
mvn clean install spring-boot:run
```

2.  Running with the prod profile

```bash
mvn clean install spring-boot:run -Dspring-boot.run.profiles=prod
```

## Resources

### Online

-   [Spring Security Password Encoding](https://www.concretepage.com/spring-5/spring-security-password-encoding)
-   [How does a JPA Proxy work and how to unproxy it with Hibernate](https://vladmihalcea.com/how-does-a-jpa-proxy-work-and-how-to-unproxy-it-with-hibernate/)
-   [Spring - Using TransactionTemplate](https://www.logicbig.com/tutorials/spring-framework/spring-data-access-with-jdbc/transaction-template.html)
-   [Spring Security: Auditing Spring Data Entities](https://blog.jdriven.com/2019/10/spring-security-auditing-spring-data-entities/)
-   [Spring Boot and Security Events with Actuator](https://blog.codeleak.pl/2017/03/spring-boot-and-security-events-with-actuator.html)
-   [Spring Boot Actuator](https://www.educba.com/spring-boot-actuator/)
-   [Using Spring Security 5 to integrate with OAuth 2-secured services such as Facebook and GitHub](https://spring.io/blog/2018/03/06/using-spring-security-5-to-integrate-with-oauth-2-secured-services-such-as-facebook-and-github)
-   [Delegation-based strategy with OAuth2UserService](https://docs.spring.io/spring-security/site/docs/5.0.7.RELEASE/reference/htmlsingle/#oauth2login-advanced-map-authorities-oauth2userservice)
-   [What is the use of ContextLoaderListener in Spring MVC Framework?](https://www.java67.com/2019/05/contextloaderlistener-in-spring-mvc-10.html)
-   [ContextLoaderListener vs DispatcherServlet](https://howtodoinjava.com/spring-mvc/contextloaderlistener-vs-dispatcherservlet/)
-   [Spring MVC - Understanding HandlerMapping](https://www.logicbig.com/tutorials/spring-framework/spring-web-mvc/handler-mapping.html)
-   [Simple Spring Security Webapp](https://www.springbyexample.org/examples/simple-spring-security-webapp-spring-config.html)
-   [Hello Spring Security Xml Config](https://docs.spring.io/spring-security/site/docs/4.2.20.RELEASE/guides/html5/helloworld-xml.html)
-   [Spring Security JSP Tag Library](https://www.javatpoint.com/spring-security-jsp-tag-library)
-   [Spring Prototype scope bean](https://zetcode.com/spring/prototypescope/)
-   [Spring - Initialization and destruction lifecycle callbacks](https://www.logicbig.com/tutorials/spring-framework/spring-core/lifecycle-callbacks.html)
-   [Spring @Required Annotation](https://www.tutorialspoint.com/spring/spring_required_annotation.htm)
-   [Spring - Bean Post Processors](https://www.tutorialspoint.com/spring/spring_bean_post_processors.htm)
-   [JDK Dynamic Proxies](https://www.byteslounge.com/tutorials/jdk-dynamic-proxies)
-   [Spring MVC - Bean Name Url Handler Mapping Example](https://www.tutorialspoint.com/springmvc/springmvc_beannameurlhandlermapping.htm)
-   [Spring Security – Securing URLs By HTTP Method](https://www.naturalprogrammer.com/blog/16385/spring-security-urls-http-method)
-   [Spring boot custom JSON Serialize – Deserialize Example](http://www.dailycodebuffer.com/spring-boot-custom-json-serialize-deserialize-example/)
-   [Spring @ModelAttribute Annotation Example](https://examples.javacodegeeks.com/enterprise-java/spring/spring-modelattribute-annotation-example/)
-   [Spring AOP AspectJ @Around Annotation Example](https://howtodoinjava.com/spring-aop/aspectj-around-annotation-example/)
-   [Spring AOP Tutorial](https://howtodoinjava.com/spring-aop-tutorial/)
-   [CGLib: The Missing Manual](https://dzone.com/articles/cglib-missing-manual)
-   [Secure Your Method Using AOP](https://dzone.com/articles/secure-your-method-using-aop)
-   [tbeauvais/example-spring-mvc-app](https://github.com/tbeauvais/example-spring-mvc-app)
-   [Spring InitializingBean and DisposableBean example](https://mkyong.com/spring/spring-initializingbean-and-disposablebean-example/)
-   [Spring @MatrixVariable at specific position in a URL](https://roytuts.com/spring-matrixvariable-at-specific-position-in-a-url/)
-   [Atomikos — multi db transaction system](https://medium.com/swlh/atomikos-multi-db-transaction-system-c16168df22e5)
-   [Spring JTA multiple resource transactions in Tomcat with Atomikos example](https://www.byteslounge.com/tutorials/spring-jta-multiple-resource-transactions-in-tomcat-with-atomikos-example)
-   [Configuring Spring and JTA without full Java EE](https://spring.io/blog/2011/08/15/configuring-spring-and-jta-without-full-java-ee)
-   [Programmatic Transaction Management](https://www.tutorialspoint.com/spring/programmatic_management.htm)
-   [Spring - Bean Post Processors](https://www.tutorialspoint.com/spring/spring_bean_post_processors.htm)
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

## About me

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
