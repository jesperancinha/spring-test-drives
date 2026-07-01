package org.jesperancinha.sftd.action.mvc2.view;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.view.AbstractView;

import java.util.Map;

import static org.springframework.http.MediaType.TEXT_HTML_VALUE;

public class TextView extends AbstractView {

    public TextView() {
        super.setContentType(TEXT_HTML_VALUE);
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        final var writer = httpServletResponse.getWriter();
        writer.println(map.get("wow"));
    }

}
