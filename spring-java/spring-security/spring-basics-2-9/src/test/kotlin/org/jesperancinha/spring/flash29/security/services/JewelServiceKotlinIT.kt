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
internal class JewelServiceKotlinIT @Autowired constructor(
    private val jewelService: JewelService,
    @MockkBean(relaxed = true)
    private val jewelRepository: JewelRepository,
) {

    @Test
    fun `should fail to create jewel when no authentication`() {
        val jewel = JewelDto
            .builder()
            .jewelType(OPAL)
            .guardian("Kitten")
            .build()
        shouldThrow<AuthenticationCredentialsNotFoundException> { jewelService.createJewel(jewel) }
    }

    @Test
    @WithMockUser(username = "gregory_kitten")
    fun `should fail to create jewel when authentication has no roles`() {
        val jewel = JewelDto
            .builder()
            .jewelType(OPAL)
            .guardian("Kitten")
            .build()
        shouldThrow<AccessDeniedException> { jewelService.createJewel(jewel) }
    }

    @Test
    @WithMockUser(username = "gregory_kitten", roles = ["ADMIN"])
    fun `should fail to create jewel when authentication has not enough roles`() {
        val jewel = JewelDto
            .builder()
            .jewelType(OPAL)
            .guardian("Kitten")
            .build()
        shouldThrow<AccessDeniedException> { jewelService.createJewel(jewel) }
    }

    @Test
    @WithMockUser(username = "gregory_kitten", roles = ["ADMIN", "WRITE"])
    fun `should fail to create jewel when guardian does not match`() {
        val jewel = JewelDto
            .builder()
            .jewelType(OPAL)
            .guardian("Kitten")
            .build()
        shouldThrow<AccessDeniedException> { jewelService.createJewel(jewel) }
    }

    @Test
    @WithMockUser(username = "gregory_kitten", roles = ["ADMIN", "WRITE"])
    fun `should create jewel ok when authenticated with right roles`() {
        val jewel = JewelDto
            .builder()
            .jewelType(OPAL)
            .guardian("gregory_kitten")
            .build()
        every { jewelRepository.save(any()) } returns Jewel(null, OPAL, "gregory_kitten")
        val jewelResult = jewelService.createJewel(jewel)
        jewelResult.jewelType shouldBe OPAL
        jewelResult.guardian shouldBe "gregory_kitten"
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