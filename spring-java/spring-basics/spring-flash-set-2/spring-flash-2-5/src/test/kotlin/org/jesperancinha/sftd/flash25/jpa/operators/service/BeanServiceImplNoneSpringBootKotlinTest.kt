package org.jesperancinha.sftd.flash25.jpa.operators.service

import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.jesperancinha.sftd.flash25.jpa.operators.repos.BeanRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.TestPropertySource

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@MockkBean(value = [BeanRepository::class], relaxed = true)
@TestPropertySource("classpath:beans.properties")
internal class BeanServiceImplNoneSpringBootKotlinTest @Autowired constructor(
    private val beanService: BeanServiceImpl,
    @Value("\${spring.datasource.url}")
    private val dataSourceUrl: String,
    private val beanRepository: BeanRepository,
    private val applicationContext: ConfigurableApplicationContext
) {
    @BeforeEach
    fun setup() {
        every { beanRepository.save(any()) } returns mockk()
        applicationContext.start()
    }

    @Test
    fun `should get production slogan`() {
        beanService.slogan shouldBe "This is just a slogan"
        dataSourceUrl shouldBe "jdbc:h2:file:~/flash25db"
        ConsolerizerComposer.outSpace()
            .green(ConsolerizerComposer.title(applicationContext.javaClass))
            .reset()
        ConsolerizerComposer
            .outSpace()
            .cyan("We cannot inject Port with NONE")
            .green("We run without any web environment")
            .reset()
    }
}