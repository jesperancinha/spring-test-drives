package org.jesperancinha.sftd.flash215.beanpostprocessor.processor;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash215.beanpostprocessor.bean.Cheese;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.GREEN;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.RED;

@Component
public class CheeseProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Cheese) {
            ((Cheese) bean).getChecks().add("Finished Initialization-" + LocalDateTime.now().toString());
            GREEN.printGeneric("This bean is named %s and its contents are %s. We have finished initialization", beanName, bean);
        } else {
            ConsolerizerComposer.outSpace().white(beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Cheese) {
            ((Cheese) bean).getChecks().add("Started Initialization-" + LocalDateTime.now().toString());
            RED.printGeneric("This bean is named %s and its contents are %s. We are starting initialization", beanName, bean);
        } else {
            ConsolerizerComposer.outSpace().white(beanName);
        }
        return bean;
    }
}
