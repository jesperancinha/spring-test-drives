package org.jesperancinha.sftd.flash25.jpa.operators.service

import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.shouldBe
import org.jesperancinha.sftd.flash25.jpa.operators.repos.BeanRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [BeanServiceImpl::class])
@MockkBean(value = [BeanRepository::class], relaxed = true)
@TestPropertySource("classpath:application.properties")
internal class BeanServiceImplKotlinTest @Autowired constructor(
    @Autowired
    private val beanService: BeanServiceImpl,
    private val applicationContext: ConfigurableApplicationContext
) {
    @BeforeEach
    fun setup() {
        applicationContext.start()
    }

    @Test
    fun `should get production slogan`() {
        beanService.slogan shouldBe "Beans are the best!"
    }
}