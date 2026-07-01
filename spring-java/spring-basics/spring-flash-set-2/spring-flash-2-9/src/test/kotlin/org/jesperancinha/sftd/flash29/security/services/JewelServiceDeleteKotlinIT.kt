package org.jesperancinha.sftd.flash29.security.services

import com.ninjasquad.springmockk.MockkBean
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.slot
import io.mockk.verify
import org.jesperancinha.sftd.flash29.security.domain.Jewel
import org.jesperancinha.sftd.flash29.security.dto.JewelDto
import org.jesperancinha.sftd.flash29.security.repository.JewelRepository
import org.jesperancinha.sftd.flash29.security.services.JewelType.PEARL
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.test.context.support.WithMockUser

@SpringBootTest
internal class JewelServiceDeleteKotlinIT @Autowired constructor(
    @Autowired
    private val jewelService: JewelService
){
    @MockkBean(relaxed = true)
    lateinit var jewelRepository: JewelRepository

    @Test
    fun testDeleteJewel_whenCallingWithoutAuthentication_thenFail() {
        val jewel = JewelDto.builder().jewelType(PEARL).guardian("ThunderKitten").build()
        shouldThrow<IllegalArgumentException> { jewelService.deleteJewel(jewel) }
    }

    @Test
    @WithMockUser(username = "ThunderKitten")
    fun testDeleteJewel_whenNoRoles_thenFail() {
        val jewel = JewelDto.builder().jewelType(PEARL).guardian("ThunderKitten").build()
        shouldThrow<AccessDeniedException>{ jewelService.deleteJewel(jewel) }
    }

    @Test
    @WithMockUser(username = "SuperKitten", roles = ["ADMIN"])
    fun testDeleteJewel_whenRolesButNoMatch_thenFail() {
        val jewel = JewelDto.builder().jewelType(PEARL).guardian("ThunderKitten").build()
        shouldThrow<AccessDeniedException>{ jewelService.deleteJewel(jewel) }
    }

    @Test
    @WithMockUser(username = "SuperKitten", roles = ["ADMIN"])
    fun testDeleteJewel_whenRolesAndMatch_thenOk() {
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