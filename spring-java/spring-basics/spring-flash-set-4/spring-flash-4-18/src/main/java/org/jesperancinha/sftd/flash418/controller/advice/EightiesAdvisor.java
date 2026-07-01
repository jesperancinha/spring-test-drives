package org.jesperancinha.sftd.flash418.controller.advice;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title;

@ControllerAdvice
public class EightiesAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotEightiesMusicException.class)
    public ResponseEntity<Object> handleCityNotFoundException(
            NotEightiesMusicException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "This music is not from the eighties");
        body.put("error", ex);

        ConsolerizerComposer.outSpace()
                .magenta(title("ResponseEntityExceptionHandler"))
                .yellow("Do bare in mind that the ResponseEntityExceptionHandler is not necessary")
                .yellow("It just means that the advisor will inherit Spring handlers which conform to Spring standards")
                .reset();

        return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
    }
}