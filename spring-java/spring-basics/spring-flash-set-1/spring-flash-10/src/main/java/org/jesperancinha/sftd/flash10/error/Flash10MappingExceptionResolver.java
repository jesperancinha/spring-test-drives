package org.jesperancinha.sftd.flash10.error;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class Flash10MappingExceptionResolver extends SimpleMappingExceptionResolver {
    public Flash10MappingExceptionResolver() {
        setWarnLogCategory(Flash10MappingExceptionResolver.class.getName());
    }

    @Override
    public String buildLogMessage(Exception e, HttpServletRequest req) {
        return "MVC exception: " + e.getLocalizedMessage();
    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest req,
                                              HttpServletResponse resp, Object handler, Exception ex) {
        ModelAndView mav = super.doResolveException(req, resp, handler, ex);
        assert mav != null;
        mav.addObject("url", req.getRequestURL());
        return mav;
    }
}