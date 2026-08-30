package org.jesperancinha.spring.flash44.handler.mapping.controller;

import org.jesperancinha.console.consolerizer.common.ConsolerizerColor;
import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.spring.flash44.handler.mapping.SpringBeanMappedInstance;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import java.util.stream.Stream;

import static org.jesperancinha.console.consolerizer.console.Consolerizer.maxLineCharsGlobal;
import static org.jesperancinha.console.consolerizer.console.Consolerizer.titleSpread;

@Controller
public class HandlerMappingController {

    private final ApplicationContext context;

    public HandlerMappingController(ApplicationContext context) {
        this.context = context;
    }

    @RequestMapping(value = "/")
    @ResponseBody
    public Stream<SpringBeanMappedInstance> handleRequest() {
        final var runningBeans = BeanFactoryUtils.beansOfTypeIncludingAncestors(
                context, HandlerMapping.class, true, false);
        maxLineCharsGlobal = 200;
        titleSpread = 200;
        ConsolerizerColor.BLUE.printGenericTitleLn("HandlerMapping");
        ConsolerizerComposer.out(" ").blue("HandlerMapping").toConsoleLn();
        runningBeans.forEach((variableName, instance) ->
                ConsolerizerComposer.out(" ")
                        .green(String.format("The order is set to-> %s", ((Ordered) instance).getOrder()))
                        .magenta(" // ")
                        .blue(String.format("The instance name is %s", variableName))
                        .magenta(" // ")
                        .brightGreen(String.format(" The instance type is %s", instance.getClass().getSimpleName()))
                        .toConsoleLn());
        return runningBeans.entrySet().stream().map(entry -> new SpringBeanMappedInstance(entry.getKey(),
                ((Ordered) entry.getValue()).getOrder(), entry.getValue().getClass().getCanonicalName()));
    }
}
