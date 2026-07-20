package org.jesperancinha.spring.service

import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.optional.shouldBeEmpty
import io.kotest.matchers.optional.shouldBePresent
import io.kotest.matchers.shouldBe
import org.jesperancinha.spring.config.SpaceRocketConfig
import org.jesperancinha.spring.entities.SpaceRocket
import org.jesperancinha.spring.entities.SpaceRocketRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.support.AnnotationConfigContextLoader
import org.springframework.transaction.annotation.Transactional

@ExtendWith(SpringExtension::class)
@ContextConfiguration(
    classes = [SpaceRocketConfig::class, SpaceRocketRepository::class],
    loader = AnnotationConfigContextLoader::class
)
@Transactional
class SpaceRocketServiceIntegrationKotlinTest @Autowired constructor(
    private val spaceRocketService: SpaceRocketService,
    private val spaceRocketRepository: SpaceRocketRepository,
) {

    @BeforeEach
    fun setUp() {
        spaceRocketRepository.deleteAll()
        spaceRocketService.addRocket(
            SpaceRocket.builder().name("Ariane1.0").engineType("A62").payLoad(5.0001).height(63.0).build()
        )
        spaceRocketService.addRocket(
            SpaceRocket.builder().name("Ariane1.1").engineType("A64").payLoad(11.0001).height(63.0).build()
        )
    }

    @AfterEach
    fun tearDown() {
        spaceRocketRepository.deleteAll()
    }

    @Test
    fun allRockets() {
        spaceRocketService.allRockets.toList()
            .shouldHaveSize(2)
    }

    @Test
    fun deleteRocket() {
        val spaceRocket =
            SpaceRocket.builder().name("Ariane").engineType("A64").payLoad(11.0001).height(63.0).build()
        spaceRocketRepository.save(spaceRocket)
        spaceRocketRepository.findById(spaceRocket.id)
            .shouldBePresent()
            .id shouldBe spaceRocket.id
        spaceRocketService.deleteRocket(spaceRocket.id)
        spaceRocketRepository.findById(spaceRocket.id).shouldBeEmpty()
    }

    @Test
    fun addRocket() {
        val rocket = spaceRocketService.addRocket(
            SpaceRocket.builder().name("Ariane").engineType("A64").payLoad(11.0001).height(63.0).build()
        )
        val spaceRocketResult = spaceRocketService.allRockets
            .first { spaceRocket1: SpaceRocket -> spaceRocket1.id == rocket.id }
        spaceRocketResult
            .shouldNotBeNull()
            .apply {
                name shouldBe "Ariane"
                engineType shouldBe "A64"
                payLoad shouldBe 11.0001
                height shouldBe 63.0
            }
    }
}