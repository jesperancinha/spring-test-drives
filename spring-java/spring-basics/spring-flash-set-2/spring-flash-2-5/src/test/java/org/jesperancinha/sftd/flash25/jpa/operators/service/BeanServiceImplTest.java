package org.jesperancinha.sftd.flash25.jpa.operators.service;

import com.ninjasquad.springmockk.MockkBean;
import org.jesperancinha.sftd.flash25.jpa.operators.repos.BeanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeanServiceImpl.class})
@MockkBean(BeanRepository.class)
@TestPropertySource("classpath:application.properties")
class BeanServiceImplTest {

    @Autowired
    private BeanServiceImpl beanService;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @BeforeEach
    public void setup(){
        applicationContext.start();
    }

    @Test
    void tesGetSlogan_whenCalled_getProductionSlogan() {
        assertThat(beanService.getSlogan()).isEqualTo("Beans are the best!");
    }
}