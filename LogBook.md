# JEOrg Spring Test Drives

### 2023/12/20

Lessons learned!

```shell
hostnamectl set-hostname '<NEW_HOSTNAME>'
```


### 2022/11/27

Due to Spring Boot update to 3.0.0 a lot of theory and practice does not match to Spring 5 anymore. Some exercises have
changed and may not reflect accurate descriptions.
From this day onwards I will make sure to bring this whole project up to dat as much as possible but please expect some
discrepancies.

Exercises and themes removed:

1. WebSecurityAdapter - This has been migrated to a Security Filter Chain
2. OAuth2 Resource service - OAuth2 with a resource server (i.e. database with tokens) has finally been deprecated and many functionalities have been removed. Modules 4-8 and 4-9 have been namely removed.

### Communication 1

It is important to take note that none of the examples presented are thought to become production versions of any sort
of product.   
The goal of this project is purely to demonstrate and provide examples of different Aspects of the Spring Framework.  
Concepts related to immutability/mutability, SOLID, ACID, 12 Factor App, Reactive Manifesto and others are present, but
they are not followed in every single example module.   
The reason being is that they take time, and adding these concerns per module can potentially lead away from the
specific goal of each module.   
These modules dot not pertain to show good practices, code styles or javadocs necessarily.    
When it comes to using Lombok, you'll find its usage more often, but it won't be used everywhere given that in some
cases it may hide some important concepts.    
These three remain a concern, but they are not the focus of this repo.   
It is also true that I will do my best to improve every single module as part of my Spring Framework journey, so these
concepts will show up more often.   
What's important to take away from this repo it to understand how the Spring Framework works.   
If you understand all of this, then please continue with your studies. Thank you! 🙏

João Esperancinha, 2021/04/21

### Communication 2

The [spring-test-drives](https://github.com/jesperancinha/spring-test-drives) repository has reached a
colossal size.	  
Therefore, it is not feasible to continue developing on this repo for new developments.   
You can consider this repo as basic overall round overview of important Spring technology developments for your day to
day work. However, this is only the beginning of the road into your Spring journey. We will continue on this in a new
repo called [Spring Master 5 Test Drives](https://github.com/jesperancinha/spring-master-5-test-drives).

João Esperancinha, 2021/05/21
