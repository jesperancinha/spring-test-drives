package org.jesperancinha.titletextadder.api.solr

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class SolrJSearcherKotlinTest {
    @Test
    @Disabled
    @Throws(Exception::class)
    fun testGetAllFilteredResults() {
        SolrJSearcher().getAllFilteredResults("*")
            .forEach{solrDocument ->
                println(solrDocument["title"])
                println(solrDocument["title_text"])
            }
    }
}