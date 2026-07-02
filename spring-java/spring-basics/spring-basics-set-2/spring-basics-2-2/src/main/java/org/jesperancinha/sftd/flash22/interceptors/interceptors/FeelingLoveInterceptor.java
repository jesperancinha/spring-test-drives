package org.jesperancinha.sftd.flash22.interceptors.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jesperancinha.sftd.flash22.interceptors.beans.FeelingLoveBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class FeelingLoveInterceptor implements HandlerInterceptor {

    private final FeelingLoveBean feelingLoveBean;

    public FeelingLoveInterceptor(FeelingLoveBean feelingLoveBean) {
        this.feelingLoveBean = feelingLoveBean;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        return feelingLoveBean.preHandle(handler);
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
        feelingLoveBean.postHandle(handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exception) {
        feelingLoveBean.afterCompletion(handler, exception);
    }
}