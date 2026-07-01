package org.jesperancinha.sftd.action.mvc2.resolver;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.action.mvc2.view.TextView;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

public class TextViewResolver implements ViewResolver, Ordered {
    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public View resolveViewName(String incomingView, Locale locale) throws Exception {
        ConsolerizerComposer.outSpace()
                .none()
                .red("The incoming view is").magenta(incomingView).newLine()
                .blue("The locale is").green(locale)
                .yellow("Using TextViewResolver");
        return new TextView();
    }
}
