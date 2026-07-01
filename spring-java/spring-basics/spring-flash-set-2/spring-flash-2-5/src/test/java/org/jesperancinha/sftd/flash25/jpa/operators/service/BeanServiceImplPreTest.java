package org.jesperancinha.sftd.flash25.jpa.operators.service;

import com.ninjasquad.springmockk.MockkBean;
import org.jesperancinha.sftd.flash25.jpa.operators.repos.BeanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BeanServiceImpl.class})
@MockkBean(BeanRepository.class)
@TestPropertySource("classpath:beans.properties")
class BeanServiceImplPreTest {

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
        final var slogan = beanService.getSlogan();
        assertThat(slogan).isEqualTo("This is just a slogan");
        final var parser = new SpelExpressionParser();
        final var evaluationContext = new StandardEvaluationContext();
        final var map = new HashMap<String, Object>();
        map.put("slogan", slogan);
        evaluationContext.setVariables(map);
        final var exp = parser.parseExpression("#slogan");
        String message = (String) exp.getValue(evaluationContext);
        assertThat(message).isEqualTo("This is just a slogan");
    }

}