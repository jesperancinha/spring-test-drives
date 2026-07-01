package org.jesperancinha.sftd.flash29.security.repository

import io.kotest.assertions.retry
import io.kotest.common.runBlocking
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import jakarta.transaction.Transactional
import jakarta.transaction.Transactional.TxType
import org.jesperancinha.sftd.flash29.security.domain.Jewel
import org.jesperancinha.sftd.flash29.security.services.JewelType.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.event.ContextStartedEvent
import org.springframework.data.repository.findByIdOrNull
import kotlin.time.Duration.Companion.seconds

@DataJpaTest
internal class JewelRepositoryKotlinTest @Autowired constructor(
    private val jewelRepository: JewelRepository,
    private val applicationContext: ConfigurableApplicationContext,
    private val publisher: ApplicationEventPublisher
) {

    private var sabinoId: Long? = null
    private var adminId: Long? = null

    @BeforeEach
    @Transactional
    fun setUp() {
        publisher.publishEvent(ContextStartedEvent(applicationContext))
        adminId = runBlocking {
            retry(10, 10.seconds) {
                jewelRepository.findAll().first { it.guardian == "admin" }
            }
        }.id
        val jewel = jewelRepository.save(
            Jewel.builder()
                .id(2)
                .jewelType(DIAMOND)
                .guardian("sabino").build()
        )
        sabinoId = jewel.shouldNotBeNull()
            .apply {
                id.shouldNotBeNull()
                jewelType shouldBe DIAMOND
                guardian shouldBe "sabino"
            }.id
    }

    @Test
    fun testGetJewelWhenReadingJewelThenResultInJewel() {
        jewelRepository.findByIdOrNull(adminId ?: throw NullPointerException())
            .shouldNotBeNull()
            .apply {
                guardian shouldBe "admin"
                jewelType shouldBe AMETHYST
            }
        jewelRepository.findByIdOrNull(sabinoId?: throw NullPointerException())
            .shouldNotBeNull()
            .apply {
                guardian shouldBe "sabino"
                jewelType shouldBe DIAMOND
            }
    }

    @Test
    @Transactional(TxType.REQUIRED)
    fun testSaveJewelWhenCreatingThenGetId() {
        jewelRepository.save(
            Jewel.builder()
                .id(3L)
                .jewelType(RUBY)
                .guardian("joao").build()
        ).shouldNotBeNull()
            .apply {
                guardian shouldBe "joao"
                jewelType shouldBe RUBY
            }
    }
}