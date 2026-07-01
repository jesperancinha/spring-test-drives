package org.jesperancinha.sftd.action.aop.aspects

import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.slot
import io.mockk.verify
import org.aspectj.lang.JoinPoint
import org.jesperancinha.sftd.action.aop.beans.OysterService
import org.jesperancinha.sftd.action.aop.model.Oyster
import org.jesperancinha.sftd.action.aop.pickers.OysterPicker
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [OysterAspect::class, OysterService::class, OysterPicker::class])
@EnableAspectJAutoProxy(proxyTargetClass = true)
internal class OysterAspectKotlinTest @Autowired constructor(
    @Autowired
    private val oysterPicker: OysterPicker,
) {

    @MockkBean(relaxed = true)
    lateinit var oysterService: OysterService

    @Test
    fun testOysterProcessing() {
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        val oysterArgumentSlot = slot<Oyster>()
        val oyster = Oyster()
        val pickup = oysterPicker.pickup(oyster)
        pickup shouldBe oyster
        verify { oysterService.oysterProcessing(capture(joinPointArgumentSlot), capture(oysterArgumentSlot)) }
        joinPointArgumentSlot
            .shouldNotBeNull()
            .first()
            .signature
            .toString() shouldBe "Oyster org.jesperancinha.sftd.action.aop.pickers.OysterPicker.pickup(Oyster)"
    }

    @Test
    fun testOysterQualityProcessing() {
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        val oysterArgumentSlot = slot<Oyster>()
        val oyster = Oyster()
        val pickup = oysterPicker.pickWithQuality(oyster)
        pickup shouldBe oyster
        verify { oysterService.oysterProcessing(capture(joinPointArgumentSlot), capture(oysterArgumentSlot)) }
        joinPointArgumentSlot
            .shouldNotBeNull()
            .first().signature
            .toString() shouldBe "Oyster org.jesperancinha.sftd.action.aop.pickers.OysterPicker.pickWithQuality(Oyster)"
    }
}