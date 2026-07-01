# spring-mastery-1 - Spring Mastery 1

## Intro

The `WebSEcurityConfigurareAdapter` has long been deprecated and it does not work anymore.

If you run this module, you'll find this exception:

```shell
java.lang.IllegalStateException: Failed to load ApplicationContext for [WebMergedContextConfiguration@6ad16c5d testClass = org.jesperancinha.sftd.mastery1.french.music.Mastery1FrenchMusicLauncherKotlinTest, locations = [], classes = [org.jesperancinha.sftd.mastery1.french.music.Mastery1FrenchMusicLauncher], contextInitializerClasses = [], activeProfiles = [], propertySourceLocations = [], propertySourceProperties = ["org.springframework.boot.test.context.SpringBootTestContextBootstrapper=true", "server.port=0"], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@33617539, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@f9b7332, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@0, org.springframework.boot.test.web.client.TestRestTemplateContextCustomizer@611f8234, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@9da1, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizerFactory$Customizer@47289387, MockkContextCustomizer(definitions=[]), org.springframework.boot.test.context.SpringBootTestAnnotation@1cb5ff63], resourceBasePath = "src/main/webapp", contextLoader = org.springframework.boot.test.context.SpringBootContextLoader, parent = null]

	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:142)
	at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:127)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.injectDependencies(DependencyInjectionTestExecutionListener.java:141)
	at org.springframework.test.context.support.DependencyInjectionTestExecutionListener.prepareTestInstance(DependencyInjectionTestExecutionListener.java:97)
	at org.springframework.test.context.TestContextManager.prepareTestInstance(TestContextManager.java:241)
	at org.springframework.test.context.junit.jupiter.SpringExtension.postProcessTestInstance(SpringExtension.java:138)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$10(ClassBasedTestDescriptor.java:377)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.executeAndMaskThrowable(ClassBasedTestDescriptor.java:382)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeTestInstancePostProcessors$11(ClassBasedTestDescriptor.java:377)
	at java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
	at java.base/java.util.stream.ReferencePipeline$2$1.accept(ReferencePipeline.java:179)
	at java.base/java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1625)
	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
	at java.base/java.util.stream.StreamSpliterators$WrappingSpliterator.forEachRemaining(StreamSpliterators.java:310)
	at java.base/java.util.stream.Streams$ConcatSpliterator.forEachRemaining(Streams.java:735)
	at java.base/java.util.stream.Streams$ConcatSpliterator.forEachRemaining(Streams.java:734)
	at java.base/java.util.stream.ReferencePipeline$Head.forEach(ReferencePipeline.java:762)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeTestInstancePostProcessors(ClassBasedTestDescriptor.java:376)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$instantiateAndPostProcessTestInstance$6(ClassBasedTestDescriptor.java:289)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.instantiateAndPostProcessTestInstance(ClassBasedTestDescriptor.java:288)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$4(ClassBasedTestDescriptor.java:278)
	at java.base/java.util.Optional.orElseGet(Optional.java:364)
	at org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$testInstancesProvider$5(ClassBasedTestDescriptor.java:277)
	at org.junit.jupiter.engine.execution.TestInstancesProvider.getTestInstances(TestInstancesProvider.java:31)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.lambda$prepare$0(TestMethodTestDescriptor.java:105)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:104)
	at org.junit.jupiter.engine.descriptor.TestMethodTestDescriptor.prepare(TestMethodTestDescriptor.java:68)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$prepare$2(NodeTestTask.java:123)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.prepare(NodeTestTask.java:123)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:90)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:155)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:155)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
	at org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
	at org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
	at org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
	at org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:147)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:127)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:90)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:55)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:102)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:54)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:114)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:86)
	at org.junit.platform.launcher.core.DefaultLauncherSession$DelegatingLauncher.execute(DefaultLauncherSession.java:86)
	at org.junit.platform.launcher.core.SessionPerRequestLauncher.execute(SessionPerRequestLauncher.java:53)
	at com.intellij.junit5.JUnit5IdeaTestRunner.startRunnerWithArgs(JUnit5IdeaTestRunner.java:57)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater$1.execute(IdeaTestRunner.java:38)
	at com.intellij.rt.execution.junit.TestsRepeater.repeat(TestsRepeater.java:11)
	at com.intellij.rt.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:35)
	at com.intellij.rt.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:235)
	at com.intellij.rt.junit.JUnitStarter.main(JUnitStarter.java:54)
Caused by: org.springframework.beans.factory.BeanDefinitionStoreException: Failed to process import candidates for configuration class [org.jesperancinha.sftd.mastery1.french.music.oauth.config.Mastery1AuthorizationServerConfigurer]: class path resource [org/springframework/security/config/annotation/web/configuration/WebSecurityConfigurerAdapter.class] cannot be opened because it does not exist
	at org.springframework.context.annotation.ConfigurationClassParser.processImports(ConfigurationClassParser.java:524)
	at org.springframework.context.annotation.ConfigurationClassParser.doProcessConfigurationClass(ConfigurationClassParser.java:304)
	at org.springframework.context.annotation.ConfigurationClassParser.processConfigurationClass(ConfigurationClassParser.java:243)
	at org.springframework.context.annotation.ConfigurationClassParser.parse(ConfigurationClassParser.java:188)
	at org.springframework.context.annotation.ConfigurationClassParser.doProcessConfigurationClass(ConfigurationClassParser.java:297)
	at org.springframework.context.annotation.ConfigurationClassParser.processConfigurationClass(ConfigurationClassParser.java:243)
	at org.springframework.context.annotation.ConfigurationClassParser.parse(ConfigurationClassParser.java:196)
	at org.springframework.context.annotation.ConfigurationClassParser.parse(ConfigurationClassParser.java:164)
	at org.springframework.context.annotation.ConfigurationClassPostProcessor.processConfigBeanDefinitions(ConfigurationClassPostProcessor.java:398)
	at org.springframework.context.annotation.ConfigurationClassPostProcessor.postProcessBeanDefinitionRegistry(ConfigurationClassPostProcessor.java:283)
	at org.springframework.context.support.PostProcessorRegistrationDelegate.invokeBeanDefinitionRegistryPostProcessors(PostProcessorRegistrationDelegate.java:344)
	at org.springframework.context.support.PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(PostProcessorRegistrationDelegate.java:115)
	at org.springframework.context.support.AbstractApplicationContext.invokeBeanFactoryPostProcessors(AbstractApplicationContext.java:745)
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:565)
	at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:146)
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:730)
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:432)
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:308)
	at org.springframework.boot.test.context.SpringBootContextLoader.lambda$loadContext$3(SpringBootContextLoader.java:137)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:59)
	at org.springframework.util.function.ThrowingSupplier.get(ThrowingSupplier.java:47)
	at org.springframework.boot.SpringApplication.withHook(SpringApplication.java:1386)
	at org.springframework.boot.test.context.SpringBootContextLoader$ContextLoaderHook.run(SpringBootContextLoader.java:543)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:137)
	at org.springframework.boot.test.context.SpringBootContextLoader.loadContext(SpringBootContextLoader.java:108)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContextInternal(DefaultCacheAwareContextLoaderDelegate.java:184)
	at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:118)
	... 72 more
Caused by: java.io.FileNotFoundException: class path resource [org/springframework/security/config/annotation/web/configuration/WebSecurityConfigurerAdapter.class] cannot be opened because it does not exist
	at org.springframework.core.io.ClassPathResource.getInputStream(ClassPathResource.java:211)
	at org.springframework.core.type.classreading.SimpleMetadataReader.getClassReader(SimpleMetadataReader.java:54)
	at org.springframework.core.type.classreading.SimpleMetadataReader.<init>(SimpleMetadataReader.java:48)
	at org.springframework.core.type.classreading.SimpleMetadataReaderFactory.getMetadataReader(SimpleMetadataReaderFactory.java:103)
	at org.springframework.boot.type.classreading.ConcurrentReferenceCachingMetadataReaderFactory.createMetadataReader(ConcurrentReferenceCachingMetadataReaderFactory.java:86)
	at org.springframework.boot.type.classreading.ConcurrentReferenceCachingMetadataReaderFactory.getMetadataReader(ConcurrentReferenceCachingMetadataReaderFactory.java:73)
	at org.springframework.core.type.classreading.SimpleMetadataReaderFactory.getMetadataReader(SimpleMetadataReaderFactory.java:81)
	at org.springframework.context.annotation.ConfigurationClassParser.asSourceClass(ConfigurationClassParser.java:610)
	at org.springframework.context.annotation.ConfigurationClassParser$SourceClass.getSuperClass(ConfigurationClassParser.java:923)
	at org.springframework.context.annotation.ConfigurationClassParser.doProcessConfigurationClass(ConfigurationClassParser.java:334)
	at org.springframework.context.annotation.ConfigurationClassParser.processConfigurationClass(ConfigurationClassParser.java:243)
	at org.springframework.context.annotation.ConfigurationClassParser.processImports(ConfigurationClassParser.java:514)
	... 98 more
```

I will try to fix this issue and eventually upgrade the module or remove it from this repo.

## Description

French language in music

Topics covered:

1.  `@ResponseBody`, `@RequestMapping`, `@RequestParam`
2.  `AuthenticationProvider`, `AuthorizationServerConfigurerAdapter`, `ResourceServerConfigurerAdapter`, `TokenStore`
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
