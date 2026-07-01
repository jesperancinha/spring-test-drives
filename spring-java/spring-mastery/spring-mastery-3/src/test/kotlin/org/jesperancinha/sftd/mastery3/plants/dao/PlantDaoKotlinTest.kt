package org.jesperancinha.sftd.mastery3.plants.dao

import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.jesperancinha.sftd.mastery3.plants.model.Plant
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser

@SpringBootTest
class PlantDaoKotlinTest @Autowired constructor(
    private val plantDao: PlantDao
) {

    @Test
    fun `should return null on failed attempt to create plant without login`() {
        val plant = plantDao.createPlant(
            Plant.builder()
                .name("Yucca")
                .scientificName("Yucca filamentosa")
                .build()
        )
        plant.shouldBeNull()
    }

    @Test
    @WithMockUser(username = "Joao")
    fun testGetFilteredList() {
        plantDao.examplePlants
            .shouldNotBeNull()
            .shouldHaveSize(1)
            .toList().apply {
                get(0).shouldNotBeNull()
                    .apply {
                        name shouldBe "Yucca"
                        owner shouldBe "Joao"
                        scientificName shouldBe "Yucca filamentosa"
                    }
                shouldForAll {
                    it.shouldNotBeNull().apply {
                        owner shouldBe "Joao"
                    }
                }
            }
    }
}