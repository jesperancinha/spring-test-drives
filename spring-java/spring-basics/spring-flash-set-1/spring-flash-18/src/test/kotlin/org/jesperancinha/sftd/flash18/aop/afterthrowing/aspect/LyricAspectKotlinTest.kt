package org.jesperancinha.sftd.flash18.aop.afterthrowing.aspect

import com.ninjasquad.springmockk.MockkBean
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.verify
import org.aspectj.lang.JoinPoint
import org.jesperancinha.sftd.flash18.aop.afterthrowing.beans.JoinPointService
import org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

/**
 * Unit tests for the Aspect Class
 */
@SpringBootTest(webEnvironment = RANDOM_PORT)
internal class LyricAspectKotlinTest @Autowired constructor(
    private val lyricsService: LyricsService
) {

    @MockkBean(relaxed = true)
    lateinit var joinPointService: JoinPointService

    /**
     * Tests the Advices that are supposed to respond to method [LyricsService.resultLyric1]
     */
    @Test
    fun `should register all advices when calling enumerate lyrics 1 after throwing advice`() {
        shouldThrow<RuntimeException> { lyricsService.enumerateLyric1() }
        val joinPoints = mutableListOf<JoinPoint>()
        val exceptions = mutableListOf<Exception>()
        verify(exactly = 1) {
            joinPointService.afterThrowingAdvice(
                capture(joinPoints), capture(exceptions)
            )
        }
        verify(exactly = 1) {
            joinPointService.afterThrowingAdvice1(
                capture(joinPoints), capture(exceptions)
            )
        }
        joinPoints
            .shouldNotBeNull()
            .shouldHaveSize(2)
            .apply {
                toList().apply {
                    get(0)
                        .shouldNotBeNull()
                        .toString() shouldBe "execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.enumerateLyric1())"
                    get(1)
                        .shouldNotBeNull()
                        .toString() shouldBe "execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.enumerateLyric1())"
                }
            }
        exceptions
            .shouldNotBeNull()
            .shouldHaveSize(2)
            .forEach { it.shouldBeTypeOf<RuntimeException>() }
    }

    @Test
    fun `should register all advices when calling enumerate lyrics 2 after throwing advice`() {
        shouldThrow<RuntimeException> { lyricsService.enumerateLyric2() }
        val joinPoints = mutableListOf<JoinPoint>()
        val exceptions = mutableListOf<Exception>()
        verify(exactly = 1) {
            joinPointService.afterThrowingAdvice(
                capture(joinPoints), capture(exceptions)
            )
        }
        verify(exactly = 1) {
            joinPointService.afterThrowingAdvice2(
                capture(joinPoints), capture(exceptions)
            )
        }
        joinPoints
            .shouldNotBeNull()
            .shouldHaveSize(2)
            .apply {
                toList().apply {
                    get(0)
                        .shouldNotBeNull()
                        .toString() shouldBe "execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.enumerateLyric2())"
                    get(1)
                        .shouldNotBeNull()
                        .toString() shouldBe "execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.enumerateLyric2())"
                }
            }
        exceptions
            .shouldNotBeNull()
            .shouldHaveSize(2)
            .forEach { it.shouldBeTypeOf<RuntimeException>() }
    }

    @Test
    fun `should register all advices when calling enumerate lyrics 3 after throwing advice`() {
        shouldThrow<RuntimeException> { lyricsService.enumerateLyric3() }
        val joinPoints = mutableListOf<JoinPoint>()
        val exceptions = mutableListOf<Exception>()
        verify(exactly = 1) {
            joinPointService.afterThrowingAdvice(
                capture(joinPoints), capture(exceptions)
            )
        }
        verify(exactly = 1) {
            joinPointService.afterThrowingAdvice3(
                capture(joinPoints), capture(exceptions)
            )
        }
        joinPoints
            .shouldNotBeNull()
            .shouldHaveSize(2)
            .apply {
                toList().apply {
                    get(0)
                        .shouldNotBeNull()
                        .toString() shouldBe "execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.enumerateLyric3())"
                    get(1)
                        .shouldNotBeNull()
                        .toString() shouldBe "execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.enumerateLyric3())"
                }
            }
        exceptions
            .shouldNotBeNull()
            .shouldHaveSize(2)
            .forEach { it.shouldBeTypeOf<RuntimeException>() }
    }

    @Test
    fun `should register all advices when calling enumerate lyrics 1 after throwing advice with 0 checks`() {
        shouldThrow<RuntimeException> { lyricsService.enumerateLyric1() }
        val joinPoints = mutableListOf<JoinPoint>()
        val exceptions = mutableListOf<Exception>()
        verify(exactly = 1) {
            joinPointService.afterThrowingAdvice(
                capture(joinPoints), capture(exceptions)
            )
        }
        verify(exactly = 1) {
            joinPointService.afterThrowingAdvice1(
                capture(joinPoints), capture(exceptions)
            )
        }
        verify(exactly = 0) {
            joinPointService.afterThrowingAdvice2(
                capture(joinPoints), capture(exceptions)
            )
        }
        verify(exactly = 0) {
            joinPointService.afterThrowingAdvice3(
                capture(joinPoints), capture(exceptions)
            )
        }
        joinPoints
            .shouldNotBeNull()
            .shouldHaveSize(2)
            .apply {
                toList().apply {
                    get(0)
                        .shouldNotBeNull()
                        .toString() shouldBe "execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.enumerateLyric1())"
                    get(1)
                        .shouldNotBeNull()
                        .toString() shouldBe "execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.enumerateLyric1())"
                }
            }
        exceptions
            .shouldNotBeNull()
            .shouldHaveSize(2)
            .forEach { it.shouldBeTypeOf<RuntimeException>() }
    }

    @Test
    fun `should register all advices when calling enumerate lyrics 2 after throwing advice with 0 checks`() {
        shouldThrow<RuntimeException> { lyricsService.enumerateLyric2() }
        val joinPoints = mutableListOf<JoinPoint>()
        val exceptions = mutableListOf<Exception>()
        verify(exactly = 1) {
            joinPointService.afterThrowingAdvice(
                capture(joinPoints), capture(exceptions)
            )
        }
        verify(exactly = 0) {
            joinPointService.afterThrowingAdvice1(
                capture(joinPoints), capture(exceptions)
            )
        }
        verify(exactly = 1) {
            joinPointService.afterThrowingAdvice2(
                capture(joinPoints), capture(exceptions)
            )
        }
        verify(exactly = 0) {
            joinPointService.afterThrowingAdvice3(
                capture(joinPoints), capture(exceptions)
            )
        }
        joinPoints
            .shouldNotBeNull()
            .shouldHaveSize(2)
            .apply {
                toList().apply {
                    get(0)
                        .shouldNotBeNull()
                        .toString() shouldBe "execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.enumerateLyric2())"
                    get(1)
                        .shouldNotBeNull()
                        .toString() shouldBe "execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.enumerateLyric2())"
                }
            }
        exceptions
            .shouldNotBeNull()
            .shouldHaveSize(2)
            .forEach { it.shouldBeTypeOf<RuntimeException>() }
    }

    @Test
    fun `should register all advices when calling enumerate lyrics 3 after throwing advice with 0 checks`() {
        shouldThrow<RuntimeException> { lyricsService.enumerateLyric3() }
        val joinPoints = mutableListOf<JoinPoint>()
        val exceptions = mutableListOf<Exception>()
        verify(exactly = 1) {
            joinPointService.afterThrowingAdvice(
                capture(joinPoints), capture(exceptions)
            )
        }
        verify(exactly = 0) {
            joinPointService.afterThrowingAdvice1(
                capture(joinPoints), capture(exceptions)
            )
        }
        verify(exactly = 0) {
            joinPointService.afterThrowingAdvice2(
                capture(joinPoints), capture(exceptions)
            )
        }
        verify(exactly = 1) {
            joinPointService.afterThrowingAdvice3(
                capture(joinPoints), capture(exceptions)
            )
        }
        joinPoints
            .shouldNotBeNull()
            .shouldHaveSize(2)
            .apply {
                toList().apply {
                    get(0)
                        .shouldNotBeNull()
                        .toString() shouldBe "execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.enumerateLyric3())"
                    get(1)
                        .shouldNotBeNull()
                        .toString() shouldBe "execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.enumerateLyric3())"
                }
            }
        exceptions
            .shouldNotBeNull()
            .shouldHaveSize(2)
            .forEach { it.shouldBeTypeOf<RuntimeException>() }
    }

    @Test
    fun `should register all advices when calling results for lyrics 4 after throwing advice with 0 checks`() {
        shouldThrow<RuntimeException> { lyricsService.resultLyric4() }
        val joinPoints = mutableListOf<JoinPoint>()
        val exceptions = mutableListOf<Exception>()
        verify(exactly = 1) {
            joinPointService.afterThrowingAdvice(
                capture(joinPoints), capture(exceptions)
            )
        }
        verify(exactly = 0) {
            joinPointService.afterThrowingAdvice1(
                capture(joinPoints), capture(exceptions)
            )
        }
        verify(exactly = 0) {
            joinPointService.afterThrowingAdvice2(
                capture(joinPoints), capture(exceptions)
            )
        }
        verify(exactly = 0) {
            joinPointService.afterThrowingAdvice3(
                capture(joinPoints), capture(exceptions)
            )
        }
        verify(exactly = 1) {
            joinPointService.afterThrowingAdviceResults(
                capture(joinPoints), capture(exceptions)
            )
        }
        joinPoints
            .shouldNotBeNull()
            .shouldHaveSize(2)
            .apply {
                toList().apply {
                    get(0)
                        .shouldNotBeNull()
                        .toString() shouldBe "execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.resultLyric4())"
                    get(1)
                        .shouldNotBeNull()
                        .toString() shouldBe "execution(void org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl.resultLyric4())"
                }
            }
        exceptions
            .shouldNotBeNull()
            .shouldHaveSize(2)
            .forEach { it.shouldBeTypeOf<RuntimeException>() }
    }
}