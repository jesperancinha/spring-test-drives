package org.jesperancinha.titletextadder.api.controller;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrDocumentList;
import org.jesperancinha.titletextadder.api.model.Title;
import org.jesperancinha.titletextadder.api.pojo.Response;
import org.jesperancinha.titletextadder.api.pojo.ResponseStatus;
import org.jesperancinha.titletextadder.api.service.TitleService;
import org.jesperancinha.titletextadder.api.solr.SolrJSearcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tta/titles")
public class JSONController {

	@RequestMapping(headers = { "content-type=application/json" }, method = RequestMethod.POST, value = "/add")
	@ResponseBody
	public Response addTitle(@RequestBody Title title) {
		final TitleService titleService = new TitleService();
		titleService.persist(title);
		return new Response(ResponseStatus.OK);
	}

	@RequestMapping(value = "/list/{filter}", method = RequestMethod.GET)
	public @ResponseBody List<Title> getTitleInJSON(@PathVariable String filter)
			throws SolrServerException, IOException {
		final SolrJSearcher searcher = new SolrJSearcher();
		final SolrDocumentList allFilteredResults = searcher.getAllFilteredResults(filter);
		List<Title> titles = new ArrayList<Title>();
		for (int i = 0; i < allFilteredResults.size(); ++i) {
			titles.add( //
					new Title( //
							allFilteredResults.get(i).get("title").toString(), //
							allFilteredResults.get(i).get("title_text").toString()) //
			);
		}
		return titles;
	}
}
