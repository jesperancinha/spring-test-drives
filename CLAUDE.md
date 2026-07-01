# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this repo is

A Spring Framework study project (Java + Kotlin) — a large collection of small, mostly-independent Maven modules,
each demonstrating one or a handful of Spring concepts (AOP, MVC, Security, Data/JPA, Actuator, WebSocket/STOMP,
transactions, testing, etc.). It is not a product: modules are not expected to share code quality standards,
architectural consistency, or even be fully finished ("Under construction" modules exist, e.g.
`spring-java/spring-mastery/spring-mastery-3`, `spring-java/title-text-adder`). Per `LogBook.md`, this repo is
considered legacy/frozen in scope on purpose — new development happens in a separate repo
(`spring-master-5-test-drives`), so avoid large architectural refactors here unless explicitly asked.

Each module directory typically has its own `Readme.md` listing the specific Spring
annotations/classes/concepts it demonstrates — check the module's `Readme.md` before editing to understand intent.

## Build / test commands

Root build (JDK 25 required — see `pom.xml` `java.version`):

```bash
mvn clean install -Dconsolerizer.show=false
# or
make build   # == make b == mvn clean install
```

Build a single module (from repo root, using its Maven artifact/reactor path), e.g.:

```bash
mvn -pl spring-java/spring-basics/spring-flash-set-2/spring-flash-2-14 -am clean install
```

Run a single test class/method with Surefire:

```bash
mvn -pl <module-path> -am test -Dtest=SomeTestClass
mvn -pl <module-path> -am test -Dtest=SomeTestClass#someMethod
```

Detect tests that silently didn't run:

```bash
mvn clean install | egrep "Tests run|----"
```

Some modules use [Testcontainers](https://www.testcontainers.org/) (Postgres, Cassandra, etc.) for integration
tests — these require a running Docker engine/Docker Desktop locally; without it, those modules' tests will fail.

Install/select the JDK via SDKMAN (check `<java.version>` in root `pom.xml` for the exact version):

```bash
sdk list java
sdk install java <JDK_VERSION>
sdk use java <JDK_VERSION>
```

Other Makefile targets (`Makefile`, `Makefile.mk`):

```bash
make rewrite              # mvn rewrite:run (OpenRewrite recipes, e.g. Spring Boot property migrations)
make deps-plugins-update   # update Maven plugin versions via external script
make deps-java-update      # update Java dependency versions via external script
```

CI (`.github/workflows/`, `.circleci/config.yml`) runs `mvn clean install jacoco:prepare-agent package jacoco:report`
followed by `mvn omni-coveragereporter:report` (Coveralls/Codecov/Codacy).

## Architecture / module layout

Root `pom.xml` is a multi-module Maven POM aggregating two language trees:

- `spring-java` — Java modules
- `spring-kotlin` — Kotlin modules

All shared dependency versions and plugin config (Lombok, Kotlin, JUnit 5/Jupiter, Mockito/AssertJ/Kotest/MockK,
Testcontainers, H2/Postgres/Cassandra/MariaDB drivers, JaCoCo, OpenRewrite) live in the **root** `pom.xml`'s
`<dependencyManagement>` — submodule POMs inherit from it via `<parent>` and generally only declare
`<modules>`/packaging, so check the root POM first when investigating a dependency version.

### `spring-java` module groups

- `spring-app-legacy` — original/oldest apps (`spring-test-drives-spring-boot`, `spring-test-drives-webapp`), JSP-based.
- `spring-basics` (aka "spring-flash") — fast-track modules grouped into sets (`spring-flash-set-1` ... `-5`),
  each set containing ~15-20 numbered submodules (`spring-flash-N`, `spring-flash-2-N`, etc.), each isolating one
  or two Spring concepts (see each set's `Readme.md` for the annotation/class list per submodule).
- `spring-mastery` — larger combined-concept modules (`spring-mastery-1` French-language-in-music app,
  `spring-mastery-2` Portuguese-language-in-music app, `spring-mastery-3` WIP).
- `spring-topics` — one Spring Boot app per topic (`spring-topic-container`).
- `spring-action` — modules disambiguating specific nuanced topics: `spring-action-aop`, `spring-action-mvc`
  (itself has sub-apps `spring-action-mvc-1`/`-2`), `spring-action-data`, `spring-action-ioc`, `spring-action-actuator`.
- `spring-apps` — larger example apps (`spring-app-1` = Cruise Ships/JDBC+persistence demo, `spring-app-2`).
- `title-text-adder` — split into `title-text-adder-api` / `title-text-adder-app` (API/app separation), plus a
  `mysql-tta-indexing` supporting module; marked "Under Construction".

### `spring-kotlin` module groups

- `spring-kotlin-mastery` — Kotlin equivalent of `spring-mastery` (`spring-kotlin-mastery-1`).
- `mix-service` — Kotlin service module.
- `xml-based-services` — legacy XML-bean-configuration-based services (`bean-registration-1`).

Kotlin compilation is wired into the Maven build via the `kotlin-maven-plugin` (with `no-arg`, `jpa`, `spring`
compiler plugins for JPA entity/Spring bean compatibility) and `build-helper-maven-plugin` to add
`src/main/kotlin` as a source root, both configured once in the root POM.

## Notes for making changes

- Don't propagate cleanups/refactors across unrelated modules — each module is a standalone teaching example;
  changes should stay scoped to the module(s) actually being discussed.
- When adding a new numbered `spring-flash-*`/`spring-mastery-*`-style module, follow the existing sibling
  modules' POM/parent structure and add a short `Readme.md` listing the Spring concepts it demonstrates, consistent
  with existing sibling readmes.
