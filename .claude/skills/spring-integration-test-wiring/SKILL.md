---
name: spring-integration-test-wiring
description: Conventions for dependency injection and mocking in Spring Boot integration tests in this project. Use this whenever writing, reviewing, or refactoring integration test classes (anything annotated @SpringBootTest, or under src/test that talks to a real Spring context), especially when deciding how to wire dependencies or mock collaborators. Also consult this before adding @Autowired, @MockBean, @MockitoBean, or field injection in any test class.
---

# Spring Boot Integration Test Wiring for all projects with tests developped in Kotlin

Rules for how dependencies get injected and mocked in this project's integration tests. Apply these whenever creating a
new integration test class or reviewing/editing an existing one.

## 1. Constructor injection only — no field injection

Integration test classes must wire their dependencies through the constructor, never through `@Autowired` fields.
This skill applies to all modules and submodules of the whole project. This means that all tests made in Kotlin are a subject of this skill.

**Do:**

```kotlin
@SpringBootTest
class JeorgActionAOPLauncherExtAOPTest @Autowired constructor(
    private val bonitoCatcher: BonitoCatcher,
    private val codCatcher: CodCatcher
) {
}
```

**Don't:**

```kotlin
@SpringBootTest
class JeorgActionAOPLauncherExtAOPTest() {

    @Autowired
    lateinit var bonitoCatcher: BonitoCatcher

    @Autowired
    lateinit var codCatcher: CodCatcher;
}
```

Notes:

- For every file that ends in IT or Test, make sure that if they need `@Autowire` annotated fields or params, that they get injected via the mentioned above `@Autowired constructor` pattern.
- JUnit 5 + Spring's `TestConstructor` support allows constructor injection in test classes the same way it works in
  production beans. If the project hasn't set `spring.test.constructor.autowire.mode=all` in
  `application-test.properties` (or via `@TestConstructor(autowireMode = ALL)`), the constructor still needs an explicit
  `@Autowired` annotation as shown above — include it only in the constructor and remove it from the fields, or the injected params.
- Keep injected fields `private val`.
- If a test only needs one or two collaborators, still use the constructor — don't fall back to field injection "just
  for this one."
- If the `@Autowired constructor` pattern is already in use, make sure that all injected params are `private val`, but also remove `@Autowire` if they have it.

## 2. Test class checklist

Before submitting/reviewing an integration test class, confirm:

- [ ] All Spring-managed dependencies come in through the constructor
- [ ] Constructor is annotated `@Autowired` (unless project-wide `TestConstructor` autowire mode is configured)
- [ ] No `@Autowired` on fields
