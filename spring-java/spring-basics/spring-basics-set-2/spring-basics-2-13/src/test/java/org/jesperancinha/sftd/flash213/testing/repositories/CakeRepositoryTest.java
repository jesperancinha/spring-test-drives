package org.jesperancinha.sftd.flash213.testing.repositories;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash213.testing.model.Cake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Locale;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
// if @DataJpaTest is commented you get:
//org.springframework.beans.factory.UnsatisfiedDependencyException:
// Error creating bean with name 'org.jesperancinha.sftd.flash213.testing.repository.CakeRepositoryTest':
// Unsatisfied dependency expressed through field 'cakeRepository';
// nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException:
// No qualifying bean of type 'org.jesperancinha.sftd.flash213.testing.repository.CakeRepository' available:
// expected at least 1 bean which qualifies as autowire candidate. Dependency annotations:
// {@org.springframework.beans.factory.annotation.Autowired(required=true)}
public class CakeRepositoryTest {

    @Autowired
    private CakeRepository cakeRepository;

    private Cake savedCake;

    @BeforeEach
    public void setUp() {
        final var cake = new Cake();
        cake.setLocale(new Locale("pt-PT", "PTR"));
        cake.setName("Bolo de Chila");
        savedCake = cakeRepository.save(cake);
    }

    @Test
    public void testGetCakeWhenGoodIdThenGetCake() {
        final Optional<Cake> optionalCake = cakeRepository.findById(savedCake.getId());

        assertThat(optionalCake.isPresent()).isTrue();

        ConsolerizerComposer.outSpace()
                .magenta("We run a %s test", DataJpaTest.class.getCanonicalName())
                .magenta("This is probably the easiest way to test that our JPA repositories work correctly")
                .blue("In our case we get:")
                .jsonPrettyPrint(optionalCake)
                .jsonPrettyPrint(optionalCake.get())
                .green("From the database")
                .reset();

        final Cake actual = optionalCake.get();
        assertThat(actual.getId()).isEqualTo(savedCake.getId());
        assertThat(actual.getName()).isEqualTo("Bolo de Chila");
        assertThat(actual.getLocale()).isEqualTo(new Locale("pt-PT", "PTR"));
    }
}