package org.jesperancinha.sftd.flash510.bean.initialization;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.jesperancinha.sftd.flash510.bean.initialization.SpringFlash510Launcher.atomicInteger;

@Component
public class SpringFlash510BeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(
            @NonNull
                    Object bean, String beanName) throws BeansException {
        if (beanName.equals("blackBean")) {
            ConsolerizerComposer.outSpace()
                    .black()
                    .bgGreen("Phase %d", atomicInteger.getAndIncrement())
                    .bgOrange("postProcessBeforeInitialization")
                    .bgGreen(bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(
            @NonNull
                    Object bean, String beanName) throws BeansException {
        if (beanName.equals("blackBean")) {
            ConsolerizerComposer.outSpace()
                    .black()
                    .bgGreen("Phase %d", atomicInteger.getAndIncrement())
                    .bgOrange("postProcessAfterInitialization")
                    .bgGreen(bean)
                    .black();
        }
        return bean;
    }
}
