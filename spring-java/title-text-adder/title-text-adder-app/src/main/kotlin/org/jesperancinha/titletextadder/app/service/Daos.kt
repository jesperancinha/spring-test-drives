package org.jesperancinha.titletextadder.app.service

import org.jesperancinha.titletextadder.app.model.Title
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import org.springframework.web.client.postForEntity
import java.net.URI

const val ADD_URL ="http://localhost:8080/title-text-adder-api/rest/tta/titles/add"
const val GET_URL = "http://localhost:8080/title-text-adder-api/rest/tta/titles/list"

@Service
class SolrTitleDao {
    val restTemplate by lazy { RestTemplate() }

    fun sendTitle(title: Title): String {
        val response = restTemplate.postForEntity<String>(URI(ADD_URL), title)
        return "You have added title: ${title.title} with text: ${title.text} and the response is ${response.body}"
    }

    fun getTitlesByTextFilter(textFilter: String): ResponseEntity<Array<String>> =
        (textFilter.ifEmpty { "*" }).let { filter ->
            try {
                val url =
                    URI("$GET_URL/$filter")
                restTemplate.getForEntity<Array<String>>(url)
            }catch (ex: HttpClientErrorException) {
                println(ex)
                ResponseEntity.ok(arrayOf("Server is reachable"))
            }
        }
}