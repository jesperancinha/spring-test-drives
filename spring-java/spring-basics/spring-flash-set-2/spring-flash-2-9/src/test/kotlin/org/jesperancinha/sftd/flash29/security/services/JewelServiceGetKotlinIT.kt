package org.jesperancinha.sftd.flash29.security.services

import com.ninjasquad.springmockk.MockkBean
import io.kotest.assertions.throwables.shouldThrow
import io.mockk.every
import org.jesperancinha.sftd.flash29.security.domain.Jewel
import org.jesperancinha.sftd.flash29.security.repository.JewelRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.test.context.support.WithMockUser
import java.util.*

@SpringBootTest
internal class JewelServiceGetKotlinIT @Autowired constructor(
    @Autowired
    private val jewelService: JewelService
) {

    @MockkBean
    lateinit var jewelRepository: JewelRepository

    @BeforeEach
    fun setUp() {
        val gregoryJewel = Optional.of(Jewel.builder().jewelType(JewelType.PEARL).guardian("gregory_kitten").build())
        val megaKitten = Optional.of(Jewel.builder().jewelType(JewelType.DIAMOND).guardian("MegaKitten").build())
        val joao = Optional.of(Jewel.builder().jewelType(JewelType.RUBY).guardian("Joao").build())
        every { jewelRepository.findById(1L) } returns gregoryJewel
        every { jewelRepository.findById(2L) } returns megaKitten
        every { jewelRepository.findById(3L) } returns joao
    }

    @Test
    fun testGetJewelById_whenNoAuthentication_thenFail() {
        shouldThrow<IllegalArgumentException> { jewelService.getJewelById(1L) }
    }

    @Test
    @WithMockUser(username = "MegaKitten")
    fun testGetJewelById_whenGuardianDoesNotMatch_thenFail() {
        shouldThrow<AccessDeniedException> { jewelService.getJewelById(3L) }
    }

    @Test
    @WithMockUser(username = "MegaKitten")
    fun testGetJewelById_whenGuardianMatch_thenOk() {
        jewelService.getJewelById(2L)
    }
}