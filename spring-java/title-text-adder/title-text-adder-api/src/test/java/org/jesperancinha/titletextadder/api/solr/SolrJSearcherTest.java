package org.jesperancinha.titletextadder.api.solr;

import org.apache.solr.common.SolrDocumentList;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class SolrJSearcherTest {

	@Test
	@Disabled
	public void testGetAllFilteredResults() throws Exception {
		final SolrJSearcher searcher = new SolrJSearcher();
		final SolrDocumentList results = searcher.getAllFilteredResults("*");
		for (org.apache.solr.common.SolrDocument result : results) {
			System.out.println(result.get("title"));
			System.out.println(result.get("title_text"));
		}
	}

}
