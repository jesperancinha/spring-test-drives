package org.jesperancinha.sftd.flash25.jpa.operators.service

import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.jesperancinha.sftd.flash25.jpa.operators.repos.BeanRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.event.ContextStartedEvent
import org.springframework.test.context.TestPropertySource

@SpringBootTest(webEnvironment = RANDOM_PORT)
@MockkBean(value = [BeanRepository::class], relaxed = true)
@TestPropertySource("classpath:beans.properties")
internal class BeanServiceImplPreSpringBootKotlinTest @Autowired constructor(
    private val beanService: BeanServiceImpl,
    private val beanRepository: BeanRepository,
    @Value("\${spring.datasource.url}")
    private val dataSourceUrl: String,
    private val publisher: ApplicationEventPublisher,
    private val applicationContext: ConfigurableApplicationContext
) {
    @BeforeEach
    fun setup() {
        every { beanRepository.save(any()) } returns mockk()
        publisher.publishEvent(ContextStartedEvent(applicationContext))
    }

    @Test
    fun `should get production slogan`() {
        beanService.slogan shouldBe "This is just a slogan"
        dataSourceUrl shouldBe "jdbc:h2:file:~/flash25db"
    }
}