package org.jesperancinha.spring.flash29.security.services

import com.ninjasquad.springmockk.MockkBean
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.slot
import io.mockk.verify
import org.jesperancinha.spring.flash29.security.domain.Jewel
import org.jesperancinha.spring.flash29.security.dto.JewelDto
import org.jesperancinha.spring.flash29.security.repository.JewelRepository
import org.jesperancinha.spring.flash29.security.services.JewelType.OPAL
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode
import org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.test.context.support.WithMockUser

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Execution(SAME_THREAD)
internal class JewelServiceUpdateKotlinIT @Autowired constructor(
    private val jewelService: JewelService,
    @MockkBean(relaxed = true)
    private val jewelRepository: JewelRepository,
) {
    @BeforeEach
    fun setup(){
        every { jewelRepository.save(any()) } returns Jewel
            .builder()
            .jewelType(OPAL)
            .guardian("MegaKitten")
            .build()
    }
    @Test
    fun `should fail to update jewel when no authentication`() {
        val jewel = JewelDto
            .builder()
            .jewelType(OPAL)
            .guardian("Kitten")
            .build()
        shouldThrow<AuthenticationCredentialsNotFoundException> { jewelService.updateJewel(jewel) }
    }

    @Test
    @WithMockUser(username = "MegaKitten")
    fun `should fail to update jewel when authentication has no roles`() {
        val jewel = JewelDto
            .builder()
            .jewelType(OPAL)
            .guardian("Kitten")
            .build()
        shouldThrow<AccessDeniedException> { jewelService.updateJewel(jewel) }
    }

    @Test
    @WithMockUser(username = "MegaKitten", roles = ["ADMIN"])
    fun `should fail to update jewel when guardian does not match`() {
        val jewel = JewelDto
            .builder()
            .jewelType(OPAL)
            .guardian("Kitten")
            .build()
        shouldThrow<AccessDeniedException> { jewelService.updateJewel(jewel) }
    }

    @Test
    @WithMockUser(username = "MegaKitten", roles = ["ADMIN"])
    fun `should update jewel ok when guardian matches`() {
        val jewel = JewelDto
            .builder()
            .jewelType(OPAL)
            .guardian("MegaKitten")
            .build()
        val jewelResult = jewelService.updateJewel(jewel)
        jewelResult.jewelType shouldBe OPAL
        jewelResult.guardian shouldBe "MegaKitten"
        val slotJewel = slot<Jewel>()
        verify { jewelRepository.save(capture(slotJewel)) }
        slotJewel.captured
            .shouldNotBeNull()
            .apply {
                guardian shouldBe jewel.guardian
                jewelType shouldBe jewel.jewelType
            }
    }
}