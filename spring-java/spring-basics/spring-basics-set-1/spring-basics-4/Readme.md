# Spring Basics Tutorial

<details>
<summary><h2><b>1. Number and Datetime Formats</b></h2></summary>

## Introduction

Exploring the

org.springframework.format.annotation.DateTimeFormat

and the

org.springframework.format.annotation.NumberFormat

annotations

Topics

1.  `org.springframework.format.annotation.DateTimeFormat`
2.  `org.springframework.format.annotation.NumberFormat`

## Endpoints

1.  Date Example

```bash
curl -X POST http://localhost:8081/time --header 'currentTime: 2014-11-01' --header 'Content-Type: application/text'
```

2.  Datetime Example

```bash
curl -X POST http://localhost:8081/time/time --header 'currentTime: 2014-12-12 10:00:00' --header 'Content-Type: application/text'
```

3.  Number Example

```bash
curl -X POST http://localhost:8081/time/dollars --header 'dollars: $1000000' --header 'Content-Type: application/text'
```
</details>


<details>
<summary><h2><b>2. Sequence generator</b></h2></summary>

## Introduction

Exploring how to make sequence generators

Topics

1.  `@SequenceGenerator`

## Endpoints

1.  Save all potatoes!

```bash
curl -X POST  http://localhost:8081/potatoes
```

2.  Get all potatoes! Notice the start and the loop

```bash
curl http://localhost:8081/potatoes
```
</details>

## About me

[![GitHub followers](https://img.shields.io/github/followers/jesperancinha.svg?label=Jesperancinha&style=for-the-badge&logo=github&color=grey "GitHub")](https://github.com/jesperancinha)
