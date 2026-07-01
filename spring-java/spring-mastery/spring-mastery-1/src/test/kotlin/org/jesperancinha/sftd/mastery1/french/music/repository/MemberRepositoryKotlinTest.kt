package org.jesperancinha.sftd.mastery1.french.music.repository

import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.longs.shouldBeGreaterThanOrEqual
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.jesperancinha.sftd.mastery1.french.music.configuration.Mastery1Configuration
import org.jesperancinha.sftd.mastery1.french.music.domain.Member
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDate

@DataJpaTest
@ExtendWith(SpringExtension::class)
internal class MemberRepositoryKotlinTest @Autowired constructor(
    @Autowired
    private val memberRepository: MemberRepository,
    @Autowired
    private val artistRepository: ArtistRepository,
) {
    @MockkBean(relaxed = true)
    lateinit var mastery1Configuration: Mastery1Configuration

    @BeforeEach
    fun setUp() {
        artistRepository.deleteAll()
        memberRepository.deleteAll()
        val member = Member()
        member.name = "Celine Dion"
        member.joinDate = LocalDate.ofYearDay(1981, 1)
        memberRepository.save(member)
    }

    @Test
    fun testGetOneWhenFindOneThenMatch() {
        memberRepository.findByIdOrNull(1L)
            .shouldNotBeNull()
            .apply {
                name shouldBe "Celine Dion"
                id shouldBeGreaterThanOrEqual 1L
            }
    }

    @Test
    fun testFindAllByNameLikeWhenFindAllThenGetAll() {
        memberRepository.findAllByNameLike("%Dion%")
            .shouldNotBeNull()
            .shouldHaveSize(1)
            .toList().apply {
                get(0).shouldNotBeNull()
                    .apply {
                        name shouldBe "Celine Dion"
                        joinDate
                            .shouldNotBeNull()
                            .apply {
                                year shouldBe 1981
                            }
                        id shouldBeGreaterThanOrEqual 1L
                    }
            }
    }

    @AfterEach
    fun tearDown() {
    }
}