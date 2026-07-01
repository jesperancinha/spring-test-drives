package org.jesperancinha.sftd.flash23.xml.interceptors.beans;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 * An instance of this service, serves the {@link org.jesperancinha.sftd.flash22.interceptors.interceptors.FeelingLoveInterceptor}
 * Each method is called separately. It decouples responsibility from the interceptor
 */
@Service
public class FeelingLoveBean {

    /**
     * Called before the request
     *
     * @param handler
     * @return
     */
    public boolean preHandle(Object handler) {
        ConsolerizerComposer.outSpace()
                .cyan("preHandle called!")
                .green("Drinkin' fine wine")
                .blue(handler)
                .reset();
        return true;
    }

    public void postHandle(Object handler,
                           ModelAndView modelAndView) {
        ConsolerizerComposer.outSpace()
                .cyan("postHandle called!")
                .green("If you like the finer things in life")
                .blue(handler)
                .reset();
    }

    public void afterCompletion(Object handler, Exception exception) {
        ConsolerizerComposer.outSpace()
                .cyan("afterCompletion called!")
                .green("Tastes like fine wine")
                .blue(handler)
                .reset();
    }
}
