package org.jesperancinha.sftd.flash25.jpa.operators.service

import io.kotest.matchers.shouldBe
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.TestPropertySource

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource("classpath:beans-fixed.properties")
internal class BeanServiceImplDefinedPortSpringBootKotlinTest @Autowired constructor(
    private val beanService: BeanServiceImpl,
    @Value("\${spring.datasource.url}")
    private val dataSourceUrl: String,
    private val applicationContext: ConfigurableApplicationContext
) {
    @LocalServerPort
    var port: Long = -1

    @BeforeEach
    fun setup() {
        applicationContext.start()
    }

    @Test
    fun `should get production slogan when endpoint is called`() {
        beanService.slogan shouldBe "This is just a fixed slogan"
        dataSourceUrl shouldBe "jdbc:h2:file:~/flash25db"
        ConsolerizerComposer
            .outSpace()
            .cyan("Server port is %d", port)
            .reset()
    }
}