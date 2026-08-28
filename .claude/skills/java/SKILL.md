---
name: java language patterns
description: Conventions for using Java. It only affects .java files
---

## 1. Use modern java conventions in the code and make necessary changes

Make sure to make the code as strict as possible to follow code conventions.
Please use these sources:


1. https://www.oracle.com/java/technologies/javase/codeconventions-programmingpractices.html
2. https://blog.jetbrains.com/idea/2024/02/java-best-practices/
3. https://google.github.io/styleguide/javaguide.html

## 2. `public static void` is reduntant in Java 25 for the `main` method.

Please make sure that wherever this is found: `public static void main(String[] args) {`
that it gets its public attribute removed as in `static void main(String[] args) {`

