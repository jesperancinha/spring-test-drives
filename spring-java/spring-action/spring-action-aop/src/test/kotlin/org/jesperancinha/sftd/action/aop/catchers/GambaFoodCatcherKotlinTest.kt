package org.jesperancinha.sftd.action.aop.catchers

import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.verify
import org.aspectj.lang.JoinPoint
import org.jesperancinha.sftd.action.aop.aspects.GambaAspect
import org.jesperancinha.sftd.action.aop.beans.GambaService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [GambaFoodCatcher::class, GambaAspect::class])
@EnableAspectJAutoProxy(proxyTargetClass = true)
internal class GambaFoodCatcherKotlinTest @Autowired constructor(
    @Autowired
    private val gambaFoodCatcher: GambaFoodCatcher,
) {
    @MockkBean(relaxed = true)
    lateinit var gambaService: GambaService

    @Test
    fun testCatchWithNetWhenCallingThenTriggerAllMatchingBeforeAdvices() {
        gambaFoodCatcher.catchWithNet()
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        verify { gambaService.beforeWithin(capture(joinPointArgumentSlot)) }
        joinPointArgumentSlot.first().signature.toString() shouldBe "Gamba org.jesperancinha.sftd.action.aop.catchers.GambaFoodCatcher.catchWithNet()"
        verify {
            gambaService.beforeWithinAnnotation(capture(joinPointArgumentSlot))
            joinPointArgumentSlot.first().signature.toString() shouldBe "Gamba org.jesperancinha.sftd.action.aop.catchers.GambaFoodCatcher.catchWithNet()"
        }
    }

    @Test
    fun testCatchByHandWhenCallingThenTriggerAllMatchingBeforeAdvices() {
        gambaFoodCatcher.catchByHand()
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        verify { gambaService.beforeAnnotation(capture(joinPointArgumentSlot)) }
        verify { gambaService.beforeWithinNoAtAnnotation(capture(joinPointArgumentSlot)) }
        verify { gambaService.beforeExecution(capture(joinPointArgumentSlot)) }
        verify { gambaService.beforeAnnotation(capture(joinPointArgumentSlot)) }
        joinPointArgumentSlot
            .shouldNotBeNull()
            .shouldHaveSize(4)
            .toList()
            .apply {
                get(0).signature
                    .toString() shouldBe "Gamba org.jesperancinha.sftd.action.aop.catchers.GambaFoodCatcher.catchByHand()"
                get(1).signature
                    .toString() shouldBe "Gamba org.jesperancinha.sftd.action.aop.catchers.GambaFoodCatcher.catchByHand()"
                get(2).signature
                    .toString() shouldBe "Gamba org.jesperancinha.sftd.action.aop.catchers.GambaFoodCatcher.catchByHand()"
                get(3).signature
                    .toString() shouldBe "Gamba org.jesperancinha.sftd.action.aop.catchers.GambaFoodCatcher.catchByHand()"
            }
    }
}