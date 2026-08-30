package org.jesperancinha.spring.tutorial.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.BRIGHT_BLUE;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.BRIGHT_CYAN;

@RestController
@RequestMapping("/session")
public class SessionController {

    @GetMapping
    public String showSessionDetails(HttpServletRequest httpServletRequest) {
        BRIGHT_CYAN.printGenericTitleLn("Getting the session via the HttpServletRequest");
        final HttpSession session = httpServletRequest.getSession();
        List<Integer> numberList = generateList(session);
        BRIGHT_BLUE.printGenericLn(numberList);
        BRIGHT_BLUE.printGenericLn(session);
        BRIGHT_BLUE.printGenericLn(session.getAttributeNames());
        session.getAttributeNames().asIterator().forEachRemaining(BRIGHT_CYAN::printGenericLn);
        return numberList.toString();
    }

    public List<Integer> generateList(HttpSession session) {
        List<Integer> numberList = (List<Integer>) session.getAttribute("numberList");
        if (Objects.isNull(numberList)) {
            final ArrayList<Integer> numbers = new ArrayList<>();
            numbers.add((int) (Math.random() * 1000));
            session.setAttribute("numberList", numbers);
            numberList = numbers;
        } else {
            numberList.add((int) (Math.random() * 1000));
        }
        return numberList;
    }
}
