# spring-basics-set-4

## Contents

1.  [spring-basics-4-1](./spring-basics-4-1) - 🏜 @Scope and prototype
2.  [spring-basics-4-2](./spring-basics-4-2) - ❎ `<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>`
3.  [spring-basics-4-3](./spring-basics-4-3) - 🛡 intercept-url, pattern, access
4.  [spring-basics-4-4](./spring-basics-4-4) - 📟 HandlerMapping
5.  [spring-basics-4-5](./spring-basics-4-5) - 🤫 ContextLoaderListener
6.  [spring-basics-4-6](./spring-basics-4-6) - 🐈 🐕 @RequestParam
7.  [spring-basics-4-7](./spring-basics-4-7) - 🌹💐🌷 ResultSetExtractor
8.  [spring-basics-4-8](./spring-basics-4-8) - 🎻 UserDetails in OAuth
9.  [spring-basics-4-9](./spring-basics-4-9) - 🧠 Monitoring
10. [spring-basics-4-10](./spring-basics-4-10) - 👩‍🦳 Security with XML
11. [spring-basics-4-11](./spring-basics-4-11) - 🛰 @RepositoryDefinition
12. [spring-basics-4-12](./spring-basics-4-12) - 🐷 Bean Id's and @Component
13. [spring-basics-4-13](./spring-basics-4-13) - 👋 SpringBootContextLoader
14. [spring-basics-4-14](./spring-basics-4-14) - 🌱 @SpringBootApplication
15. [spring-basics-4-15](./spring-basics-4-15) - 💧 AbstractHttpMessageConverter and HttpMessageConverter
16. [spring-basics-4-16](./spring-basics-4-16) - 🧛🏻‍♂️ MockMvc
17. [spring-basics-4-17](./spring-basics-4-17) - 👻 management.server.port
18. [spring-basics-4-18](./spring-basics-4-18) - 🕺🏻 @ControllerAdvice, @ExceptionHandler, ResponseEntityExceptionHandler
19. [spring-basics-4-19](./spring-basics-4-19) - 🦩 TestApplicationContext
20. [spring-basics-4-20](./spring-basics-4-20) - 🛣 @Rollback and @Commit and @Transactional and @Test

## Resources

### Online

-   [Spring Boot Starters](https://www.javatpoint.com/spring-boot-starters)
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
