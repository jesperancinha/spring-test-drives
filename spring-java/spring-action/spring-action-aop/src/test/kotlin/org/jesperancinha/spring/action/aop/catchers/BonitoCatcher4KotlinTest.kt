package org.jesperancinha.spring.action.aop.catchers

import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.verify
import org.aspectj.lang.JoinPoint
import org.jesperancinha.spring.action.aop.aspects.BonitoAspect4
import org.jesperancinha.spring.action.aop.beans.Bonito4Service
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.annotation.ImportResource
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.annotation.DirtiesContext

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [BonitoCatcher::class, BonitoAspect4::class])
@ImportResource("classpath:bean.xml")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
internal class BonitoCatcher4KotlinTest @Autowired constructor(
    private val bonitoCatcher: BonitoCatcher,
    @MockkBean(relaxed = true)
    private val bonito4Service: Bonito4Service,
) {

    @Test
    fun testCatchWithNetwhenCalledThenTriggerAllAdvices() {
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        bonitoCatcher.catchWithNet()
        verify { bonito4Service.beforeAnyCatch(capture(joinPointArgumentSlot)) }
        joinPointArgumentSlot.first().signature
            .toString() shouldBe "Bonito org.jesperancinha.spring.action.aop.catchers.BonitoCatcher.catchWithNet()"
    }

    @Test
    fun testCatchWithFishingPoleWhenCalledThenTriggerAllAdvices() {
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        bonitoCatcher.catchWithFishingPole()
        verify { bonito4Service.beforeAnyCatch(capture(joinPointArgumentSlot)) }
        joinPointArgumentSlot.first().signature
            .toString() shouldBe "Bonito org.jesperancinha.spring.action.aop.catchers.BonitoCatcher.catchWithFishingPole()"
    }

    @Test
    fun testCatchByHandWhenCalledThenTriggerAllAdvices() {
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        bonitoCatcher.catchByHand()
        verify { bonito4Service.beforeAnyCatch(capture(joinPointArgumentSlot)) }
        joinPointArgumentSlot
            .first()
            .signature
            .toString() shouldBe "Bonito org.jesperancinha.spring.action.aop.catchers.BonitoCatcher.catchByHand()"
    }

    @Test
    fun testCatchByHandExtraWhenCalledThenTriggerAllAdvices() {
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        bonitoCatcher.catchByHandExtra()
        verify { bonito4Service.beforeAnyCatch(capture(joinPointArgumentSlot)) }
        joinPointArgumentSlot
            .first()
            .signature
            .toString() shouldBe "void org.jesperancinha.spring.action.aop.catchers.BonitoCatcher.catchByHandExtra()"
    }

    @Test
    fun testCatchWithClawsWhenCalledThenTriggerAllAdvices() {
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        bonitoCatcher.catchWithClaws()
        verify { bonito4Service.beforeAnyCatch(capture(joinPointArgumentSlot)) }
        verify { bonito4Service.waitPrivatelyForFishCatch(capture(joinPointArgumentSlot)) }
        joinPointArgumentSlot
            .shouldNotBeNull()
            .shouldHaveSize(2)
            .map { joinPoint: JoinPoint -> joinPoint.signature.toString() }
            .shouldContainAll(
                "Bonito org.jesperancinha.spring.action.aop.catchers.BonitoCatcher.catchWithClaws()",
                "Bonito org.jesperancinha.spring.action.aop.catchers.BonitoCatcher.catchWithClaws()"
            )
    }

    @Test
    fun testCatchWithSuperSonicWavesWhenCalledThenTriggerAllAdvices() {
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        bonitoCatcher.catchWithSuperSonicWaves()
        verify { bonito4Service.beforeAnyCatch(capture(joinPointArgumentSlot)) }
        joinPointArgumentSlot.first().signature
            .toString() shouldBe "Bonito org.jesperancinha.spring.action.aop.catchers.BonitoCatcher.catchWithSuperSonicWaves()"

    }

    @Test
    fun testCatchWithABucketWhenCalledThenTriggerAllAdvices() {
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        bonitoCatcher.catchWithABucket()
        verify { bonito4Service.beforeAnyCatch(capture(joinPointArgumentSlot)) }
        joinPointArgumentSlot.first().signature
            .toString() shouldBe "Bonito org.jesperancinha.spring.action.aop.catchers.BonitoCatcher.catchWithABucket()"

    }

    @Test
    fun testCatchWithLoveWhenCalledThenTriggerAllAdvices() {
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        bonitoCatcher.catchWithLove()
        verify { bonito4Service.beforeAnyCatch(capture(joinPointArgumentSlot)) }
        joinPointArgumentSlot.first().signature
            .toString() shouldBe "Bonito org.jesperancinha.spring.action.aop.catchers.BonitoCatcher.catchWithLove()"
    }
}