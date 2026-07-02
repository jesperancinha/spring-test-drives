package org.jesperancinha.sftd.flash9.socksjs.controllers;

import org.jesperancinha.sftd.flash9.socksjs.domain.Present;
import org.jesperancinha.sftd.flash9.socksjs.domain.Request;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class PresentController {

    @MessageMapping("/request")
    @SendTo({"/business/present", "/business/notification"})
    public Present sendPresent(final Request request) {
        return new Present(request);
    }
}
