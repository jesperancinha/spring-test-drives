package org.jesperancinha.titletextadder.app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
@EnableWebMvc
class XmlTextAdderLauncher

fun main(args: Array<String>) {
    SpringApplication.run(XmlTextAdderLauncher::class.java, *args)
}