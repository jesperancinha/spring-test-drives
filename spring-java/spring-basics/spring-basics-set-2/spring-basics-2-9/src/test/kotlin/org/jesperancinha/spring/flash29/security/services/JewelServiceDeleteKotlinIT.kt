package org.jesperancinha.spring.flash29.security.services

import com.ninjasquad.springmockk.MockkBean
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.slot
import io.mockk.verify
import org.jesperancinha.spring.flash29.security.domain.Jewel
import org.jesperancinha.spring.flash29.security.dto.JewelDto
import org.jesperancinha.spring.flash29.security.repository.JewelRepository
import org.jesperancinha.spring.flash29.security.services.JewelType.PEARL
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.test.context.support.WithMockUser

@SpringBootTest
internal class JewelServiceDeleteKotlinIT @Autowired constructor(
    private val jewelService: JewelService,
    @MockkBean(relaxed = true)
    private val jewelRepository: JewelRepository,
) {
    @Test
    fun `should fail to delete jewel when calling without authentication`() {
        val jewel = JewelDto.builder().jewelType(PEARL).guardian("ThunderKitten").build()
        shouldThrow<IllegalArgumentException> { jewelService.deleteJewel(jewel) }
    }

    @Test
    @WithMockUser(username = "ThunderKitten")
    fun `should fail to delete jewel when no roles`() {
        val jewel = JewelDto.builder().jewelType(PEARL).guardian("ThunderKitten").build()
        shouldThrow<AccessDeniedException>{ jewelService.deleteJewel(jewel) }
    }

    @Test
    @WithMockUser(username = "SuperKitten", roles = ["ADMIN"])
    fun `should fail to delete jewel when roles do not match`() {
        val jewel = JewelDto.builder().jewelType(PEARL).guardian("ThunderKitten").build()
        shouldThrow<AccessDeniedException>{ jewelService.deleteJewel(jewel) }
    }

    @Test
    @WithMockUser(username = "SuperKitten", roles = ["ADMIN"])
    fun `should delete jewel ok when roles match`() {
        val superKittenJewel = JewelDto.builder().jewelType(PEARL).guardian("SuperKitten").build()
        jewelService.deleteJewel(superKittenJewel)
        val slotJewel = slot<Jewel>()
        verify { jewelRepository.delete(capture(slotJewel)) }

        slotJewel.captured
            .shouldNotBeNull()
            .apply {
                id.shouldBeNull()
                jewelType shouldBe PEARL
                guardian shouldBe "SuperKitten"
            }
    }
}