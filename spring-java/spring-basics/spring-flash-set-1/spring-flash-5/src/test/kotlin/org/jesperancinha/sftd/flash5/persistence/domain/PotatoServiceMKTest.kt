package org.jesperancinha.sftd.flash5.persistence.domain;

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.WordSpec
import io.kotest.core.test.TestCase
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [PotatoService::class])
@MockkBean(PotatoRepository::class)
class PotatoServiceMKTest(
    @Autowired
    val potatoService: PotatoService,
    @Autowired
    val potatoRepository: PotatoRepository
) : WordSpec() {

    override suspend fun beforeEach(testCase: TestCase) {
        super.beforeEach(testCase)
        val newPotato1 = Potato();
        newPotato1.form = "Sweet";
        val newPotato2 = Potato();
        newPotato2.form = "Kind";
        every { potatoRepository.findById(1L) } returns (Optional.of(newPotato1));
        every { potatoRepository.findById(2L) } returns (Optional.of(newPotato2));
    }

    init {
        "testing potato service" should {
            "create potato when calling method and retrieve it"{
                val newPotato = Potato();
                newPotato.form = "Kindest of them all"

                val savedPotato = Potato();
                savedPotato.form = "Kindest of the universe"
                savedPotato.id = 3L

                val potatoSlot = slot<Potato>()

                every { potatoRepository.save(capture(potatoSlot)) } returns (savedPotato);

                val potato = potatoService.createPotato(newPotato);

                potato.shouldNotBeNull()
                potato.form shouldBe "Kindest of the universe"
                potato.id.shouldNotBeNull()
                potato.id shouldBe 3L

                verify { potatoRepository.save(newPotato) }

                val potatoSave = potatoSlot.captured
                potatoSave.shouldNotBeNull()
                potatoSave.id.shouldBeNull()
                potatoSave.form shouldBe "Kindest of them all"
            }

            "retrieve all potatoes when calling all of them"{
                val newPotato1 = Potato();
                newPotato1.form = "Sweet";
                val newPotato2 = Potato();
                newPotato2.form = "Kind";
                every { potatoRepository.findAll() } returns listOf(newPotato1, newPotato2);

                val all = potatoService.allPotatoes;

                all shouldHaveSize 2
                all shouldContainAll listOf(newPotato1, newPotato2)

                verify { potatoRepository.findAll() }
            }
        }
    }

}