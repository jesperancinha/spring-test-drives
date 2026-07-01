package org.jesperancinha.sftd

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD
import org.springframework.test.annotation.DirtiesContext.HierarchyMode.EXHAUSTIVE
import org.springframework.test.context.TestExecutionListeners
import org.springframework.test.context.support.DirtiesContextTestExecutionListener

@SpringBootTest("test=123")
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD, hierarchyMode = EXHAUSTIVE)
@TestExecutionListeners(DirtiesContextTestExecutionListener::class)
class JeorgSpringKotlinMastery1ApplicationTest {

    @Test
    fun contextLoads() {
        main(arrayOf())
    }

}
