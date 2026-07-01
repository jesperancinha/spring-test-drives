package org.jesperancinha.sftd.action.aop.catchers

import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.shouldBe
import io.mockk.verify
import org.aspectj.lang.JoinPoint
import org.jesperancinha.sftd.action.aop.aspects.TunaAspect
import org.jesperancinha.sftd.action.aop.beans.Bonito4Service
import org.jesperancinha.sftd.action.aop.beans.TunaService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [MegaTunaCatcher::class, TunaAspect::class])
@EnableAspectJAutoProxy(proxyTargetClass = true)
internal class MegaTunaCatcherKotlinTest(
    @Autowired
    private val megaTunaCatcher: MegaTunaCatcher
) {
    @MockkBean(relaxed = true)
    lateinit var tunaService: TunaService

    @MockkBean(relaxed = true)
    lateinit var bonito4Service: Bonito4Service

    @Test
    fun catchWithNet() {
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        megaTunaCatcher.catchWithNet()
        verify { tunaService.beforeCatching(capture(joinPointArgumentSlot)) }
        joinPointArgumentSlot.first().signature
            .toString() shouldBe "Tuna org.jesperancinha.sftd.action.aop.catchers.MegaTunaCatcher.catchWithNet()"
    }
}