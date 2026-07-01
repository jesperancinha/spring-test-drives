package org.jesperancinha.sftd.flash25.jpa.operators.service

import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.jesperancinha.sftd.flash25.jpa.operators.repos.BeanRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.ContextStartedEvent
import org.springframework.expression.spel.standard.SpelExpressionParser
import org.springframework.expression.spel.support.StandardEvaluationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [BeanServiceImpl::class])
@MockkBean(value = [BeanRepository::class], relaxed = true)
@TestPropertySource("classpath:beans.properties")
internal class BeanServiceImplPreKotlinTest @Autowired constructor(
    private val beanService: BeanServiceImpl,
    private val publisher: ApplicationEventPublisher,
    private val applicationContext: ApplicationContext,
    private val beanRepository: BeanRepository
) {

    @BeforeEach
    fun setup() {
        every { beanRepository.save(any()) } returns mockk()
        publisher.publishEvent(ContextStartedEvent(applicationContext))
    }

    @Test
    fun `should get production slogan`() {
        val parser = SpelExpressionParser()
        val evaluationContext = StandardEvaluationContext()
        val map = HashMap<String, Any>()
        map["slogan"] = beanService.slogan
            .shouldNotBeNull().also { it shouldBe "This is just a slogan" }
        evaluationContext.setVariables(map)
        val exp = parser.parseExpression("#slogan")
        val message = exp.getValue(evaluationContext)
        message shouldBe "This is just a slogan"
    }
}