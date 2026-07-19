package org.jesperancinha.spring.action.aop.catchers

import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.verify
import org.aspectj.lang.JoinPoint
import org.jesperancinha.spring.action.aop.aspects.*
import org.jesperancinha.spring.action.aop.beans.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.stream.IntStream
import org.springframework.test.annotation.DirtiesContext

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [GambaFoodCatcher::class, BonitoCatcher::class, MackerelCatcher::class, MegaTunaCatcher::class, SardineCatcher::class, ShrimpCatcher::class, TunaCatcher::class, GambaAspect::class, MasterAspect::class, BonitoAspect::class, BonitoAspect2::class, BonitoAspect3::class, BonitoAspect4::class])
@EnableAspectJAutoProxy(proxyTargetClass = true)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
internal class GambaFoodMasterCatcherKotlinTest @Autowired constructor(
    private val gambaFoodCatcher: GambaFoodCatcher,
    private val bonitoCatcher: BonitoCatcher,
    private val mackerelCatcher: MackerelCatcher,
    private val megaTunaCatcher: MegaTunaCatcher,
    private val sardineCatcher: SardineCatcher,
    private val shrimpCatcher: ShrimpCatcher,
    private val tunaCatcher: TunaCatcher,
    @MockkBean(relaxed = true)
    private val gambaService: GambaService,
    @MockkBean(relaxed = true)
    private val masterService: MasterService,
    @MockkBean(relaxed = true)
    private val tunaService: TunaService,
    @MockkBean(relaxed = true)
    private val codService: CodService,
    @MockkBean(relaxed = true)
    private val bonito1Service: Bonito1Service,
    @MockkBean(relaxed = true)
    private val bonito2Service: Bonito2Service,
    @MockkBean(relaxed = true)
    private val bonito3Service: Bonito3Service,
    @MockkBean(relaxed = true)
    private val bonito4Service: Bonito4Service,
) {

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
        joinPointArgumentSlot.last().signature.toString() shouldBe "Gamba org.jesperancinha.spring.action.aop.catchers.GambaFoodCatcher.catchWithNet()"
        verify { gambaService.beforeWithinAnnotation(capture(joinPointArgumentSlot))}
        joinPointArgumentSlot.last().signature.toString() shouldBe "Gamba org.jesperancinha.spring.action.aop.catchers.GambaFoodCatcher.catchWithNet()"
        verify { masterService.masterize(capture(joinPointArgumentSlot))}
        joinPointArgumentSlot.last().signature.toString() shouldBe "Gamba org.jesperancinha.spring.action.aop.catchers.GambaFoodCatcher.catchWithNet()"
        verify { bonito2Service.beforeAnyCatch(capture(joinPointArgumentSlot))}
        IntStream.range(3, joinPointArgumentSlot.size - 1)
            .forEach { i: Int ->
                joinPointArgumentSlot[i].signature.toString() shouldNotBe "Gamba org.jesperancinha.spring.action.aop.catchers.GambaFoodCatcher.catchWithNet()"
            }
    }
}