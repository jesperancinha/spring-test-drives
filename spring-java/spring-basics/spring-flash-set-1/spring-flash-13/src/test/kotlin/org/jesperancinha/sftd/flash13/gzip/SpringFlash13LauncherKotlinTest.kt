package org.jesperancinha.sftd.flash13.gzip

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest
@ContextConfiguration(classes = [SpringFlash13Launcher::class])
internal class SpringFlash13LauncherKotlinTest(
    @Autowired
    private val mockMvc: MockMvc
) {

    @Test
    fun `should test context`() {
    }

    @Test
    @Throws(Exception::class)
    fun testCallsToCssWhenNormalThenGetNormal() {
        mockMvc.perform(MockMvcRequestBuilders.get("/flash13.styles.css"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.content().string(
                    """.car {
  text-align: left;
  font-weight: bolder;
}"""
                )
            )
    }

    @Test
    @Throws(Exception::class)
    fun testCallsToCssWhenGzipThenGetContents() {
        mockMvc.perform(MockMvcRequestBuilders.get("/original.styles.css"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(
                MockMvcResultMatchers.content().string(
                    """.car {
  text-align: center;
  font-weight: bolder;
}"""
                )
            )
    }
}