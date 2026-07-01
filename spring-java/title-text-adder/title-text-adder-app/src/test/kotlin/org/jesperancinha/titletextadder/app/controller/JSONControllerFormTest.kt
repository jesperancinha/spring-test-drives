package org.jesperancinha.titletextadder.app.controller

import com.ninjasquad.springmockk.MockkBean
import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.jesperancinha.titletextadder.app.model.Title
import org.jesperancinha.titletextadder.app.service.SolrTitleDao
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.ResponseEntity
import org.springframework.ui.ModelMap
import org.springframework.validation.BindingResult

@SpringBootTest
internal class JSONControllerFormTest @Autowired constructor(
    val jsonControllerForm: JSONControllerForm
) {

    @MockkBean(relaxed = true)
    lateinit var solrTitleDao: SolrTitleDao

    @Test
    fun `should show form`() {
        jsonControllerForm.showForm() shouldBe "addsTitle"
    }

    @Test
    fun `should add title`() {
        val title = Title().apply { title = "title" }
        val bindingResult = mockk<BindingResult>(relaxed = true)
        every { bindingResult.hasErrors() } returns false
        jsonControllerForm.addTitle(title, bindingResult)
        verify { solrTitleDao.sendTitle(title) }
    }

    @Test
    fun `should show titles`() {
        every { solrTitleDao.getTitlesByTextFilter("filter") } returns ResponseEntity.ok(arrayOf("title1", "title2"))
        val modelMap = ModelMap()
        jsonControllerForm.showTitles(modelMap,"filter") shouldBe "showTitles"
        (modelMap.getAttribute("Titles") as Array<String>).shouldContainInOrder("title1", "title2")
    }
}