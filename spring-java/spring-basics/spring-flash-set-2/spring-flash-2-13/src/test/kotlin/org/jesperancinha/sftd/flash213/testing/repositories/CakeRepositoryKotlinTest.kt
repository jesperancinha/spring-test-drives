package org.jesperancinha.sftd.flash213.testing.repositories

import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer
import org.jesperancinha.sftd.flash213.testing.model.Cake
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
@DataJpaTest // if @DataJpaTest is commented you get:
//org.springframework.beans.factory.UnsatisfiedDependencyException:
// Error creating bean with name 'org.jesperancinha.sftd.flash213.testing.repository.CakeRepositoryTest':
// Unsatisfied dependency expressed through field 'cakeRepository';
// nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException:
// No qualifying bean of type 'org.jesperancinha.sftd.flash213.testing.repository.CakeRepository' available:
// expected at least 1 bean which qualifies as autowire candidate. Dependency annotations:
// {@org.springframework.beans.factory.annotation.Autowired(required=true)}

class CakeRepositoryKotlinTest(
    @Autowired
    private val cakeRepository: CakeRepository
) {

    var savedCake:Cake? = null

    @BeforeEach
    fun setUp() {
        val cake = Cake()
        cake.locale = Locale("pt-PT", "PTR")
        cake.name = "Bolo de Chila"
        savedCake = cakeRepository.save(cake)
    }

    @Test
    fun testGetCakeWhenGoodIdThenGetCake() {
       cakeRepository.findById(savedCake.shouldNotBeNull().id)
           .shouldNotBeNull()
           .apply {
               isPresent.shouldBeTrue()
               ConsolerizerComposer.outSpace()
                   .magenta("We run a %s test", DataJpaTest::class.java.canonicalName)
                   .magenta("This is probably the easiest way to test that our JPA repositories work correctly")
                   .blue("In our case we get:")
                   .jsonPrettyPrint(this)
                   .jsonPrettyPrint(this.get())
                   .green("From the database")
                   .reset()
               get().apply {
                   id shouldBe savedCake.shouldNotBeNull().id
                   name shouldBe "Bolo de Chila"
                   locale shouldBe Locale("pt-PT", "PTR")
               }
           }
    }
}