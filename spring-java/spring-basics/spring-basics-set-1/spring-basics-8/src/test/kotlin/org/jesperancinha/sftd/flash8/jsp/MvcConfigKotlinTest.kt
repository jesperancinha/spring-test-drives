package org.jesperancinha.sftd.flash8.jsp

import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.types.shouldBeTypeOf
import org.junit.jupiter.api.Test
import org.springframework.web.servlet.view.InternalResourceViewResolver

internal class MvcConfigKotlinTest {
    private val mvcConfig = MvcConfig()

    @Test
    fun `should just test MVC just to show that this is possible`() {
        mvcConfig.viewResolver
            .shouldBeTypeOf<InternalResourceViewResolver>()
            .shouldNotBeNull()
    }
}