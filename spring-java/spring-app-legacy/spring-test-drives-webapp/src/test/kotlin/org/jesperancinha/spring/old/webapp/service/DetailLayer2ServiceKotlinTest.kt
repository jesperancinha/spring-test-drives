package org.jesperancinha.spring.old.webapp.service

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.jesperancinha.spring.old.webapp.model.DetailEntity
import org.jesperancinha.spring.old.webapp.repository.DetailRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ActiveProfiles("test")
class DetailLayer2ServiceKotlinTest @Autowired constructor(
    private val detailService: DetailService,
    private val detailRepository: DetailRepository
) {

    lateinit var element: DetailEntity

    @BeforeEach
    @Transactional
    fun setUp() {
        detailRepository.deleteAll()
        element = addOneElement()
    }

    private fun addOneElement() = detailRepository.save(DetailEntity.builder().name(NAME_1).city(CITY_1).build())


    @Test
    fun `should ged details by id`() {
        detailService.findDetailById(element.id)
            .shouldNotBeNull()
            .apply {
                name shouldBe NAME_1
                city.shouldBeNull()
            }
        detailRepository.deleteAll()
        detailRepository.findByIdOrNull(element.id)
            .shouldBeNull()
        detailService.findDetailById(1)
            .shouldNotBeNull()
            .apply {
                name shouldBe NAME_1
                city.shouldBeNull()
            }
        element = addOneElement()
        detailRepository.findByIdOrNull(element.id)
            .shouldNotBeNull()
            .apply {
                name shouldBe NAME_1
                city.shouldBeNull()
            }
        detailRepository.findByIdOrNull(element.id)
            .shouldNotBeNull()
            .apply {
                name shouldBe NAME_1
                city.shouldBeNull()
            }
    }

    companion object {
        private const val NAME_1 = "Name1"
        private const val CITY_1 = "City1"
    }
}