package org.jesperancinha.sftd.service

import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.optional.shouldBeEmpty
import io.kotest.matchers.optional.shouldBePresent
import io.kotest.matchers.shouldBe
import org.jesperancinha.sftd.config.SpaceRocketConfig
import org.jesperancinha.sftd.entities.SpaceRocket
import org.jesperancinha.sftd.entities.SpaceRocketRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.context.support.AnnotationConfigContextLoader

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [SpaceRocketConfig::class], loader = AnnotationConfigContextLoader::class)
class SpaceRocketServiceIntegrationKotlinTest @Autowired constructor(
    val spaceRocketService: SpaceRocketService,
    val spaceRocketRepository: SpaceRocketRepository,
) {

    @BeforeEach
    fun setUp() {
        spaceRocketService.addRocket(
            SpaceRocket.builder().id(2).name("Ariane1.0").engineType("A62").payLoad(5.0001).height(63.0).build()
        )
        spaceRocketService.addRocket(
            SpaceRocket.builder().id(3).name("Ariane1.1").engineType("A64").payLoad(11.0001).height(63.0).build()
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
            SpaceRocket.builder().id(4).name("Ariane").engineType("A64").payLoad(11.0001).height(63.0).build()
        spaceRocketRepository.save(spaceRocket)
        spaceRocketRepository.findById(4)
            .shouldBePresent()
            .id shouldBe 4
        spaceRocketService.deleteRocket(4)
        spaceRocketRepository.findById(4).shouldBeEmpty()
    }

    @Test
    fun addRocket() {
        val spaceRocket =
            SpaceRocket.builder().id(1).name("Ariane").engineType("A64").payLoad(11.0001).height(63.0).build()
        spaceRocketService.addRocket(spaceRocket)
        val spaceRocketResult = spaceRocketService.allRockets
            .first { spaceRocket1: SpaceRocket -> spaceRocket1.id == 1 }
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