package org.jesperancinha.sftd.flash7.session.controllers;

import org.jesperancinha.sftd.flash7.session.exceptions.CarNotAvailableException;
import org.jesperancinha.sftd.flash7.session.exceptions.FlowerNotAvailableException;
import org.jesperancinha.sftd.flash7.session.exceptions2.CarNotAvailableException2;
import org.jesperancinha.sftd.flash7.session.exceptions2.FlowerNotAvailableException2;
import org.jesperancinha.sftd.flash7.session.exceptions2.FourWheelNotAvailableException;
import org.jesperancinha.sftd.flash7.session.exceptions2.PotteryNotAvailableException;
import org.jesperancinha.sftd.flash7.session.handlers.ErrorCar;
import org.jesperancinha.sftd.flash7.session.handlers.ErrorFlower;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.GREEN;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.MAGENTA;

@RestController
public class ProductController {

    @GetMapping(path = "tulips",
            produces = "application/json")
    public String getTulips() {
        GREEN.printGenericTitleLn("You are still reaching this endpoint -> getTulips");
        return "You just got a bunch of tulips!";
    }

    @ResponseStatus(code = HttpStatus.OK,
            reason = "This is an error, but it's ok")
    @GetMapping(path = "/tulips/ok",
            produces = "application/json")
    public String getTulipsOk() {
        GREEN.printGenericTitleLn("You are still reaching this endpoint -> getTulipsOk");
        return "You just got a bunch of tulips!";
    }

    @ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE,
            reason = "We are not available at the moment!")
    @GetMapping("/tulips/error")
    public String getTulipsError() {
        GREEN.printGenericTitleLn("You are still reaching this endpoint -> getTulipsError");
        return "You didn't get a bunch of tulips!";
    }

    @GetMapping("/flowers/{flower}")
    public String getFlower(final @PathVariable("flower")
                                    String flower) {
        GREEN.printGenericTitleLn("You are requesting Flower %s", flower);
        throw new FlowerNotAvailableException(flower);
    }

    @GetMapping("/cars/{car}")
    public String getCar(final @PathVariable("car")
                                 String car) {
        GREEN.printGenericTitleLn("You are requesting Car %s", car);
        throw new CarNotAvailableException(car);
    }


    @GetMapping("/flowers/local/{flower}")
    public @ResponseBody
    ModelAndView getFlowerLocal(final @PathVariable("flower")
                                        String flower) {
        GREEN.printGenericTitleLn("You are requesting Flower %s", flower);
        throw new FlowerNotAvailableException2(flower);
    }

    @GetMapping("/cars/local/{car}")
    public @ResponseBody
    ModelAndView getCarLocal(final @PathVariable("car")
                                     String car) {
        GREEN.printGenericTitleLn("You are requesting Car %s", car);
        throw new CarNotAvailableException2(car);
    }

    @GetMapping("/pottery/{pottery}")
    public String getPottery(final @PathVariable("pottery")
                                     String pottery) {
        GREEN.printGenericTitleLn("You are requesting Pottery %s", pottery);
        throw new PotteryNotAvailableException(pottery);
    }

    @GetMapping("/fourwheels/{fourwheels}")
    public String getFourwheels(final @PathVariable("fourwheels")
                                        String fourwheels) {
        GREEN.printGenericTitleLn("You are requesting 4 Wheels %s", fourwheels);
        throw new FourWheelNotAvailableException(fourwheels);
    }


    // javax.servlet.ServletException: Circular view path [errorpage]: would dispatch back to the current handler URL [/cars/local/errorpage] again. Check your ViewResolver setup! (Hint: This may be the result of an unspecified view, due to default view name generation.)
    //	at org.springframework.web.servlet.view.InternalResourceView.prepareForRendering(InternalResourceView.java:210) ~[spring-webmvc-5.2.0.RELEASE.jar:5.2.0.RELEASE]
    //	at org.springframework.web.servlet.view.InternalResourceView.renderMergedOutputModel(InternalResourceView.java:148) ~[spring-webmvc-5.2.0.RELEASE.jar:5.2.0.RELEASE]
    //	at org.springframework.web.servlet.view.AbstractView.render(AbstractView.java:317) ~[spring-webmvc-5.2.0.RELEASE.jar:5.2.0.RELEASE]
    //	at org.springframework.web.servlet.DispatcherServlet.render(DispatcherServlet.java:1373) ~[spring-webmvc-5.2.0.RELEASE.jar:5.2.0.RELEASE]
    //	at org.springframework.web.servlet.DispatcherServlet.processDispatchResult(DispatcherServlet.java:1118) ~[spring-webmvc-5.2.0.RELEASE.jar:5.2.0.RELEASE]
    //	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1057) ~[spring-webmvc-5.2.0.RELEASE.jar:5.2.0.RELEASE]
    //	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:943) ~[spring-webmvc-5.2.0.RELEASE.jar:5.2.0.RELEASE]
    //	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006) ~[spring-webmvc-5.2.0.RELEASE.jar:5.2.0.RELEASE]
    //	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898) ~[spring-webmvc-5.2.0.RELEASE.jar:5.2.0.RELEASE]
    @ExceptionHandler(CarNotAvailableException2.class)
    public final ModelAndView handleCarExceptions(final CarNotAvailableException2 carNotAvailableException) {
        final String message = Arrays.stream(carNotAvailableException.getStackTrace()).distinct().map(StackTraceElement::toString).collect(Collectors.joining());
        final ErrorCar errorCar = new ErrorCar();
        errorCar.setMessage(message);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("errorpage");
        modelAndView.addObject("fail", errorCar);
        MAGENTA.printGenericTitleLn(errorCar);
        return modelAndView;
    }


    // javax.servlet.ServletException: Circular view path [errorpage]: would dispatch back to the current handler URL [/cars/local/errorpage] again. Check your ViewResolver setup! (Hint: This may be the result of an unspecified view, due to default view name generation.)
    //	at org.springframework.web.servlet.view.InternalResourceView.prepareForRendering(InternalResourceView.java:210) ~[spring-webmvc-5.2.0.RELEASE.jar:5.2.0.RELEASE]
    //	at org.springframework.web.servlet.view.InternalResourceView.renderMergedOutputModel(InternalResourceView.java:148) ~[spring-webmvc-5.2.0.RELEASE.jar:5.2.0.RELEASE]
    //	at org.springframework.web.servlet.view.AbstractView.render(AbstractView.java:317) ~[spring-webmvc-5.2.0.RELEASE.jar:5.2.0.RELEASE]
    //	at org.springframework.web.servlet.DispatcherServlet.render(DispatcherServlet.java:1373) ~[spring-webmvc-5.2.0.RELEASE.jar:5.2.0.RELEASE]
    //	at org.springframework.web.servlet.DispatcherServlet.processDispatchResult(DispatcherServlet.java:1118) ~[spring-webmvc-5.2.0.RELEASE.jar:5.2.0.RELEASE]
    //	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1057) ~[spring-webmvc-5.2.0.RELEASE.jar:5.2.0.RELEASE]
    //	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:943) ~[spring-webmvc-5.2.0.RELEASE.jar:5.2.0.RELEASE]
    //	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006) ~[spring-webmvc-5.2.0.RELEASE.jar:5.2.0.RELEASE]
    //	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898) ~[spring-webmvc-5.2.0.RELEASE.jar:5.2.0.RELEASE]
    @ExceptionHandler(FlowerNotAvailableException2.class)
    public final ModelAndView handleFlowerExceptions(final FlowerNotAvailableException2 flowerNotAvailableException) {
        final String message = Arrays.stream(flowerNotAvailableException.getStackTrace()).distinct().map(StackTraceElement::toString).collect(Collectors.joining());
        final ErrorFlower errorFlower = new ErrorFlower(message, HttpStatus.NOT_FOUND);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("errorpage");
        modelAndView.addObject("fail", errorFlower);
        MAGENTA.printGenericTitleLn(errorFlower);
        return modelAndView;
    }

}
