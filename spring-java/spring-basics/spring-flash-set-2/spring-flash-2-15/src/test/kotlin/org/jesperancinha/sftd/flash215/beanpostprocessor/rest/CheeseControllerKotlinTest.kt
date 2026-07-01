package org.jesperancinha.sftd.flash215.beanpostprocessor.rest

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.matchers.shouldBe
import org.jesperancinha.sftd.flash215.beanpostprocessor.bean.Cheese
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
internal class CheeseControllerKotlinTest(
    @Autowired
    private val mockMvc: MockMvc
) {

    @Test
    @Throws(Exception::class)
    fun `should make a call to get all cheeses`() {
        val expectedCheeses = listOf(
            Cheese.builder().name("Camembert").url("https://en.wikipedia.org/wiki/Camembert").checks(ArrayList())
                .build(),
            Cheese.builder().name("Brie").url("https://en.wikipedia.org/wiki/Brie").checks(ArrayList()).build(),
            Cheese.builder().name("Sao Jorge").url("https://en.wikipedia.org/wiki/S%C3%A3o_Jorge_cheese")
                .checks(ArrayList()).build()
        )
        val objectMapper = ObjectMapper()
        val mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
        val contentAsString = mvcResult.response.contentAsString
        val cheeses = objectMapper.readValue(contentAsString, Array<Cheese>::class.java)
        cheeses[0]
            .apply {
                name shouldBe expectedCheeses[0].name
                url shouldBe expectedCheeses[0].url
            }
        cheeses[1]
            .apply {
                name shouldBe expectedCheeses[1].name
                url shouldBe expectedCheeses[1].url
            }

        cheeses[2]
            .apply {
                name shouldBe expectedCheeses[2].name
                url shouldBe expectedCheeses[2].url
            }
    }
}