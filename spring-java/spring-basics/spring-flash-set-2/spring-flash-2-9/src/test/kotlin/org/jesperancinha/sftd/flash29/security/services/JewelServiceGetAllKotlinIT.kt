package org.jesperancinha.sftd.flash29.security.services

import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.every
import org.jesperancinha.sftd.flash29.security.domain.Jewel
import org.jesperancinha.sftd.flash29.security.repository.JewelRepository
import org.jesperancinha.sftd.flash29.security.services.JewelType.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

/**
 * In this case, we also do not use SpringExtension, because we want security to be activated.
 * When using Spring Extension, some features of Spring like Spring Security, won't be activated, resulting in this particular case, in a test that seems to work both ways, but it only actually tests the security aspect with [SpringBootTest]
 */
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = JewelServiceImpl.class)
@SpringBootTest
internal class JewelServiceGetAllKotlinIT @Autowired constructor(
    @Autowired
    private val jewelService: JewelService
) {

    @MockkBean(relaxed = true)
    lateinit var jewelRepository: JewelRepository

    @BeforeEach
    fun setUp() {
        val gregoryJewel = Jewel.builder().jewelType(PEARL).guardian("gregory_kitten").build()
        val megaKittenJewel = Jewel.builder().jewelType(DIAMOND).guardian("MegaKitten").build()
        val joaoJewel = Jewel.builder().jewelType(RUBY).guardian("Joao").build()
        val jewelList = listOf(gregoryJewel, megaKittenJewel, joaoJewel)
        every { jewelRepository.findAll() } returns jewelList
    }

    @Test
    fun testGetAll_whenNoAuthentication_thenStillListAll() {
        jewelService
            .shouldNotBeNull()
            .all
            .shouldNotBeNull()
            .shouldHaveSize(3)
            .toList()
            .apply {
                get(0)
                    .shouldNotBeNull()
                    .apply {
                        guardian shouldBe "gregory_kitten"
                        jewelType shouldBe PEARL
                    }
                get(1)
                    .apply {
                        guardian shouldBe "MegaKitten"
                        jewelType shouldBe DIAMOND
                    }
                get(2)
                    .apply {
                        guardian shouldBe "Joao"
                        jewelType shouldBe RUBY
                    }
            }
    }
}