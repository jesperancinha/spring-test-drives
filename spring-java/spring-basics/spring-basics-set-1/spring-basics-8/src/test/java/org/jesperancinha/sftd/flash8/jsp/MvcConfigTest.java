package org.jesperancinha.sftd.flash8.jsp;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.assertj.core.api.Assertions.assertThat;

class MvcConfigTest {

    private final MvcConfig mvcConfig = new MvcConfig();

    @Test
    void testGetViewResolverWhenCalledThenGoodViewResolver() {
        final var viewResolver = mvcConfig.getViewResolver();
        assertThat(viewResolver).isInstanceOf(InternalResourceViewResolver.class);
        final InternalResourceViewResolver internalResourceViewResolver = (InternalResourceViewResolver) viewResolver;
        assertThat(internalResourceViewResolver).isNotNull();
    }
}