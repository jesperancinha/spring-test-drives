package org.jesperancinha.sftd.action.aop.aspects

import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldNotBeSameInstanceAs
import io.mockk.slot
import io.mockk.verify
import org.aspectj.lang.JoinPoint
import org.jesperancinha.sftd.action.aop.beans.HarvestingService
import org.jesperancinha.sftd.action.aop.fishing.Fisher
import org.jesperancinha.sftd.action.aop.fishing.Shrimper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [HarvestingAspect::class, HarvestingService::class, Fisher::class, Shrimper::class])
@EnableAspectJAutoProxy(proxyTargetClass = true)
internal class HarvestingAspectKotlinTest @Autowired constructor(
    @Autowired
    private val fisher: Fisher,
    @Autowired
    private val shrimper: Shrimper,
) {
    @MockkBean(relaxed = true)
    lateinit var harvestingService: HarvestingService

    @Test
    fun testAnyHarvesterWhenCall3HarversterTypesNSubsThenTriggerAdvices() {
        fisher.harvest()
        shrimper.harvest()
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        verify { harvestingService.thisHarvester(capture(joinPointArgumentSlot)) }
        joinPointArgumentSlot.map { joinPoint: JoinPoint -> joinPoint.signature.toString() }
            .shouldNotBeNull()
            .shouldHaveSize(2)
            .shouldContainAll(
                "Shrimp org.jesperancinha.sftd.action.aop.fishing.Shrimper.harvest()",
                "SeaFood org.jesperancinha.sftd.action.aop.fishing.Fisher.harvest()"
            )
        verify { harvestingService.targetShrimper(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot.first().signature.toString() shouldBe "Shrimp org.jesperancinha.sftd.action.aop.fishing.Shrimper.harvest()"
        verify { harvestingService.targetFisher(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot.map { joinPoint: JoinPoint -> joinPoint.signature.toString() }
            .shouldNotBeNull()
            .shouldHaveSize(2)
            .shouldContainAll(
                "Shrimp org.jesperancinha.sftd.action.aop.fishing.Shrimper.harvest()",
                "SeaFood org.jesperancinha.sftd.action.aop.fishing.Fisher.harvest()"
            )
        verify { harvestingService.targetHarvester(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot.map { joinPoint: JoinPoint -> joinPoint.signature.toString() }
            .shouldNotBeNull()
            .shouldHaveSize(2)
            .shouldContainAll(
                "Shrimp org.jesperancinha.sftd.action.aop.fishing.Shrimper.harvest()",
                "SeaFood org.jesperancinha.sftd.action.aop.fishing.Fisher.harvest()"
            )
        verify { harvestingService.thisFisher(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot.map { joinPoint: JoinPoint -> joinPoint.signature.toString() }
            .shouldNotBeNull()
            .shouldHaveSize(2)
            .shouldContainAll(
                "Shrimp org.jesperancinha.sftd.action.aop.fishing.Shrimper.harvest()",
                "SeaFood org.jesperancinha.sftd.action.aop.fishing.Fisher.harvest()"
            )
        verify { harvestingService.thisShrimper(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot.map { joinPoint: JoinPoint -> joinPoint.signature.toString() }
            .shouldNotBeNull()
            .shouldHaveSize(1)
            .shouldContainAll("Shrimp org.jesperancinha.sftd.action.aop.fishing.Shrimper.harvest()")
    }


    @Test
    fun testAnyHarvesterWhenCallReferenceToFisherThenTriggerOnlyFisherRefAdvicesAndAllInstanceShrimperAdvices() {
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        shrimper.harvest()
        verify { harvestingService.thisHarvester(capture(joinPointArgumentSlot)) }
        joinPointArgumentSlot.map { joinPoint: JoinPoint -> joinPoint.signature.toString() }
            .shouldNotBeNull()
            .shouldHaveSize(1)
            .shouldContainAll("Shrimp org.jesperancinha.sftd.action.aop.fishing.Shrimper.harvest()")
        verify { harvestingService.targetShrimper(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot.first().signature.toString() shouldBe "Shrimp org.jesperancinha.sftd.action.aop.fishing.Shrimper.harvest()"
        verify { harvestingService.targetFisher(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot.map { joinPoint: JoinPoint -> joinPoint.signature.toString() }
            .shouldNotBeNull()
            .shouldHaveSize(1)
            .shouldContainAll("Shrimp org.jesperancinha.sftd.action.aop.fishing.Shrimper.harvest()")
        verify { harvestingService.targetHarvester(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot.map { joinPoint: JoinPoint -> joinPoint.signature.toString() }
            .shouldNotBeNull()
            .shouldHaveSize(1)
            .shouldContainAll("Shrimp org.jesperancinha.sftd.action.aop.fishing.Shrimper.harvest()")
        verify { harvestingService.thisFisher(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot.map { joinPoint: JoinPoint -> joinPoint.signature.toString() }
            .shouldNotBeNull()
            .shouldHaveSize(1)
            .shouldContainAll("Shrimp org.jesperancinha.sftd.action.aop.fishing.Shrimper.harvest()")
        verify { harvestingService.thisShrimper(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot.map { joinPoint: JoinPoint -> joinPoint.signature.toString() }
            .shouldNotBeNull()
            .shouldHaveSize(1)
            .shouldContainAll("Shrimp org.jesperancinha.sftd.action.aop.fishing.Shrimper.harvest()")
    }

    @Test
    fun testFishAnythingWhenCallReferenceToFisherThenTriggerOnlyFisherRefAdvicesAndAllInstanceShrimperAdvices() {
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        shrimper.justFishAnything()
        verify { harvestingService.thisHarvester(capture(joinPointArgumentSlot)) }
        joinPointArgumentSlot.map { joinPoint: JoinPoint -> joinPoint.signature.toString() }
            .shouldNotBeNull()
            .shouldHaveSize(1)
            .shouldContainAll("void org.jesperancinha.sftd.action.aop.fishing.Fisher.justFishAnything()")
        verify { harvestingService.targetShrimper(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot.first().signature.toString() shouldBe "void org.jesperancinha.sftd.action.aop.fishing.Fisher.justFishAnything()"
        verify { harvestingService.targetFisher(capture(joinPointArgumentSlot.also { it.clear() })) }

        joinPointArgumentSlot.map { joinPoint: JoinPoint -> joinPoint.signature.toString() }
            .shouldNotBeNull()
            .shouldHaveSize(1)
            .shouldContainAll("void org.jesperancinha.sftd.action.aop.fishing.Fisher.justFishAnything()")
        verify { harvestingService.targetHarvester(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot.map { joinPoint: JoinPoint -> joinPoint.signature.toString() }
            .shouldNotBeNull()
            .shouldHaveSize(1)
            .shouldContainAll("void org.jesperancinha.sftd.action.aop.fishing.Fisher.justFishAnything()")
        verify { harvestingService.thisFisher(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot.map { joinPoint: JoinPoint -> joinPoint.signature.toString() }
            .shouldNotBeNull()
            .shouldHaveSize(1)
            .shouldContainAll("void org.jesperancinha.sftd.action.aop.fishing.Fisher.justFishAnything()")
        verify(exactly = 0) { harvestingService.thisShrimper(capture(joinPointArgumentSlot.also { it.clear() })) }
    }

    @Test
    fun testFishShrimpWhenCallParameterThenTriggerOnlyFisherRefAdvicesAndAllInstanceShrimperAdvices() {
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        val shrimperArgumentSlot = slot<Shrimper>()
        shrimper.secretHarvest()
        verify {
            harvestingService.passingTargetArgument(
                capture(joinPointArgumentSlot),
                capture(shrimperArgumentSlot)
            )
        }
        joinPointArgumentSlot.first().signature.toString() shouldBe "void org.jesperancinha.sftd.action.aop.fishing.Shrimper.secretHarvest()"
        shrimperArgumentSlot.captured.shouldNotBeNull()
        verify { harvestingService.thisHarvester(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot
            .map { joinPoint: JoinPoint -> joinPoint.signature.toString() }
            .shouldNotBeNull()
            .shouldHaveSize(1)
            .shouldContainAll(
                "void org.jesperancinha.sftd.action.aop.fishing.Shrimper.secretHarvest()",
            )
        verify { harvestingService.targetShrimper(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot.first().signature.toString() shouldBe "void org.jesperancinha.sftd.action.aop.fishing.Shrimper.secretHarvest()"
        verify { harvestingService.targetFisher(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot.map { joinPoint: JoinPoint -> joinPoint.signature.toString() }
            .shouldNotBeNull()
            .shouldHaveSize(1)
            .shouldContainAll("void org.jesperancinha.sftd.action.aop.fishing.Shrimper.secretHarvest()")
        verify { harvestingService.targetHarvester(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot.map { joinPoint: JoinPoint -> joinPoint.signature.toString() }
            .shouldNotBeNull()
            .shouldHaveSize(1)
            .shouldContainAll("void org.jesperancinha.sftd.action.aop.fishing.Shrimper.secretHarvest()")
        verify { harvestingService.thisFisher(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot.map { joinPoint: JoinPoint -> joinPoint.signature.toString() }
            .shouldNotBeNull()
            .shouldHaveSize(1)
            .shouldContainAll("void org.jesperancinha.sftd.action.aop.fishing.Shrimper.secretHarvest()")
        verify { harvestingService.thisShrimper(capture(joinPointArgumentSlot.also { it.clear() })) }
        joinPointArgumentSlot.map { joinPoint: JoinPoint -> joinPoint.signature.toString() }
            .shouldNotBeNull()
            .shouldHaveSize(1)
            .shouldContainAll("void org.jesperancinha.sftd.action.aop.fishing.Shrimper.secretHarvest()")
    }

    @Test
    fun testFishShrimpWhenCallParameterThenProxyServiceGetsIntoAdvice() {
        val joinPointArgumentSlot = mutableListOf<JoinPoint>()
        val shrimperArgumentSlot = slot<Shrimper>()
        shrimper.secretHarvest()
        verify {
            harvestingService.passingTargetArgument(
                capture(joinPointArgumentSlot),
                capture(shrimperArgumentSlot)
            )
        }
        joinPointArgumentSlot
            .shouldNotBeNull()
            .first().signature.toString() shouldBe "void org.jesperancinha.sftd.action.aop.fishing.Shrimper.secretHarvest()"
        shrimperArgumentSlot.captured.shouldNotBeNull()
            .apply {
                id shouldBe shrimper.id
            }.apply {
                shouldNotBeSameInstanceAs(shrimper)
            }
    }
}