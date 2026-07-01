package org.jesperancinha.sftd.action.aop.catchers

import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.verify
import org.aspectj.lang.JoinPoint
import org.jesperancinha.sftd.action.aop.aspects.BonitoAspect2
import org.jesperancinha.sftd.action.aop.beans.Bonito2Service
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [BonitoCatcher::class, BonitoAspect2::class])
@EnableAspectJAutoProxy(proxyTargetClass = true)
internal class BonitoCatcherKotlinTest @Autowired constructor(
    private val bonitoCatcher: BonitoCatcher,
) {
    @MockkBean(relaxed = true)
    lateinit var bonito2Service: Bonito2Service

    @Test
    fun testCatchWithNetWhenCalledThenTriggerAllAdvices() {
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        bonitoCatcher.catchWithNet()
        verify { bonito2Service.beforeAnyCatch(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot.first().signature
            .toString() shouldBe "Bonito org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchWithNet()"
    }

    @Test
    fun testCatchWithFishingPoleWhenCalledThenTriggerAllAdvices() {
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        bonitoCatcher.catchWithFishingPole()
        verify { bonito2Service.beforeAnyCatch(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot.first().signature.toString() shouldBe "Bonito org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchWithFishingPole()"
    }

    @Test
    fun testCatchByHandWhenCalledThenTriggerAllAdvices() {
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        bonitoCatcher.catchByHand()
        verify { bonito2Service.beforeAnyCatch(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot.first().signature.toString() shouldBe "Bonito org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchByHand()"
    }

    @Test
    fun testCatchByHandExtraWhenCalledThenTriggerAllAdvices() {
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        bonitoCatcher.catchByHandExtra()
        verify { bonito2Service.beforeAnyCatch(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot.first().signature.toString() shouldBe "void org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchByHandExtra()"
    }

    @Test
    fun testCatchWithClawsWhenCalledThenTriggerAllAdvices() {
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        bonitoCatcher.catchWithClaws()
        verify { bonito2Service.beforeAnyCatch(capture(joinPointArgumentSlot.also { it.clear() })) }
        verify { bonito2Service.waitForFishCatch(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot
            .shouldNotBeNull()
            .shouldHaveSize(1)
            .map { joinPoint: JoinPoint -> joinPoint.signature.toString() }
            .shouldContainAll(
                "Bonito org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchWithClaws()",
            )
    }

    @Test
    fun testCatchWithSuperSonicWavesWhenCalledThenTriggerAllAdvices() {
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        bonitoCatcher.catchWithSuperSonicWaves()
        verify { bonito2Service.beforeAnyCatch(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot.first().signature.toString() shouldBe "Bonito org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchWithSuperSonicWaves()"
    }

    @Test
    fun testCatchWithABucketWhenCalledThenTriggerAllAdvices() {
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        bonitoCatcher.catchWithABucket()
        verify { bonito2Service.beforeAnyCatch(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot.first().signature.toString() shouldBe "Bonito org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchWithABucket()"
    }

    @Test
    fun testCatchWithLoveWhenCalledThenTriggerAllAdvices() {
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        bonitoCatcher.catchWithLove()
        verify { bonito2Service.beforeAnyCatch(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot.first().signature.toString() shouldBe "Bonito org.jesperancinha.sftd.action.aop.catchers.BonitoCatcher.catchWithLove()"
    }
}