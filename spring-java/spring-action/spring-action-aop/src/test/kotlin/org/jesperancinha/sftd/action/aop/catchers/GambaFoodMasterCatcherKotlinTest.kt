package org.jesperancinha.sftd.action.aop.catchers

import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.verify
import org.aspectj.lang.JoinPoint
import org.jesperancinha.sftd.action.aop.aspects.*
import org.jesperancinha.sftd.action.aop.beans.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.stream.IntStream

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [GambaFoodCatcher::class, BonitoCatcher::class, MackerelCatcher::class, MegaTunaCatcher::class, SardineCatcher::class, ShrimpCatcher::class, TunaCatcher::class, GambaAspect::class, MasterAspect::class, BonitoAspect::class, BonitoAspect2::class, BonitoAspect3::class, BonitoAspect4::class])
@EnableAspectJAutoProxy(proxyTargetClass = true)
internal class GambaFoodMasterCatcherKotlinTest @Autowired constructor(
    val gambaFoodCatcher: GambaFoodCatcher,
    val bonitoCatcher: BonitoCatcher,
    val mackerelCatcher: MackerelCatcher,
    val megaTunaCatcher: MegaTunaCatcher,
    val sardineCatcher: SardineCatcher,
    val shrimpCatcher: ShrimpCatcher,
    val tunaCatcher: TunaCatcher,
) {
    @MockkBean(relaxed = true)
    lateinit var gambaService: GambaService

    @MockkBean(relaxed = true)
    lateinit var masterService: MasterService

    @MockkBean(relaxed = true)
    lateinit var tunaService: TunaService

    @MockkBean(relaxed = true)
    lateinit var codService: CodService

    @MockkBean(relaxed = true)
    lateinit var bonito1Service: Bonito1Service

    @MockkBean(relaxed = true)
    lateinit var bonito2Service: Bonito2Service

    @MockkBean(relaxed = true)
    lateinit var bonito3Service: Bonito3Service

    @MockkBean(relaxed = true)
    lateinit var bonito4Service: Bonito4Service

    @Test
    fun testCatchWithNetWhenCallingMasterAnnotatedMethodThenTriggerTheRightAdvices() {
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        gambaFoodCatcher.catchWithNet()
        bonitoCatcher.catchWithNet()
        mackerelCatcher.catchWithNet()
        megaTunaCatcher.catchWithNet()
        sardineCatcher.catchWithNet()
        shrimpCatcher.catchWithNet()
        tunaCatcher.catchWithNet()
        verify { gambaService.beforeWithin(capture(joinPointArgumentSlot)) }
        joinPointArgumentSlot.last().signature.toString() shouldBe "Gamba org.jesperancinha.sftd.action.aop.catchers.GambaFoodCatcher.catchWithNet()"
        verify { gambaService.beforeWithinAnnotation(capture(joinPointArgumentSlot))}
        joinPointArgumentSlot.last().signature.toString() shouldBe "Gamba org.jesperancinha.sftd.action.aop.catchers.GambaFoodCatcher.catchWithNet()"
        verify { masterService.masterize(capture(joinPointArgumentSlot))}
        joinPointArgumentSlot.last().signature.toString() shouldBe "Gamba org.jesperancinha.sftd.action.aop.catchers.GambaFoodCatcher.catchWithNet()"
        verify { bonito2Service.beforeAnyCatch(capture(joinPointArgumentSlot))}
        IntStream.range(3, joinPointArgumentSlot.size - 1)
            .forEach { i: Int ->
                joinPointArgumentSlot[i].signature.toString() shouldNotBe "Gamba org.jesperancinha.sftd.action.aop.catchers.GambaFoodCatcher.catchWithNet()"
            }
    }
}