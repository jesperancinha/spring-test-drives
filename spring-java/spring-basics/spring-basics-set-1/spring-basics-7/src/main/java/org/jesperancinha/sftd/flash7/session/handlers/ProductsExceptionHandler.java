package org.jesperancinha.sftd.flash7.session.handlers;

import org.jesperancinha.sftd.flash7.session.exceptions.CarNotAvailableException;
import org.jesperancinha.sftd.flash7.session.exceptions.FlowerNotAvailableException;
import org.jesperancinha.sftd.flash7.session.exceptions2.CarNotAvailableException2;
import org.jesperancinha.sftd.flash7.session.exceptions2.FlowerNotAvailableException2;
import org.jesperancinha.sftd.flash7.session.exceptions2.FourWheelNotAvailableException;
import org.jesperancinha.sftd.flash7.session.exceptions2.PotteryNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.CYAN;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.RED;

@ControllerAdvice
public class ProductsExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CarNotAvailableException.class)
    public final ResponseEntity<ErrorCar> handleCarExceptions(final CarNotAvailableException carNotAvailableException, final WebRequest webRequest) {
        final String message = Arrays.stream(carNotAvailableException.getStackTrace()).distinct().map(StackTraceElement::toString).collect(Collectors.joining());
        final ErrorCar errorCar = new ErrorCar();
        errorCar.setMessage(message);
        CYAN.printGenericTitleLn("You have just created %s", errorCar);
        return new ResponseEntity<>(errorCar, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FlowerNotAvailableException.class)
    public final ResponseEntity<ErrorFlower> handleFlowerExceptions(final FlowerNotAvailableException flowerNotAvailableException, final WebRequest webRequest) {
        final String message = Arrays.stream(flowerNotAvailableException.getStackTrace()).distinct().map(StackTraceElement::toString).collect(Collectors.joining());
        final ErrorFlower errorFlower = new ErrorFlower(message, HttpStatus.NOT_FOUND);
        CYAN.printGenericTitleLn("You have just created %s", errorFlower);
        return new ResponseEntity<>(errorFlower, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({FourWheelNotAvailableException.class, PotteryNotAvailableException.class})
    public final ResponseEntity<MixErrorMessage> handleExtrasException(
            final Exception exception) {
        final String message = Arrays.stream(exception.getStackTrace()).distinct().map(StackTraceElement::toString).collect(Collectors.joining());
        final MixErrorMessage mixErrorMessage = new MixErrorMessage(message);
        return new ResponseEntity<>(mixErrorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CarNotAvailableException2.class)
    public final ResponseEntity<ErrorCar> handleCarExceptions(final CarNotAvailableException2 carNotAvailableException, final WebRequest webRequest) {
        RED.printGenericTitleLn("This will never be called! Local defined handlers have precedence");
        final String message = Arrays.stream(carNotAvailableException.getStackTrace()).distinct().map(StackTraceElement::toString).collect(Collectors.joining());
        final ErrorCar errorCar = new ErrorCar();
        errorCar.setMessage(message);
        return new ResponseEntity<>(errorCar, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FlowerNotAvailableException2.class)
    public final ResponseEntity<ErrorFlower> handleFlowerExceptions(final FlowerNotAvailableException2 flowerNotAvailableException, final WebRequest webRequest) {
        RED.printGenericTitleLn("This will never be called! Local defined handlers have precedence");
        final String message = Arrays.stream(flowerNotAvailableException.getStackTrace()).distinct().map(StackTraceElement::toString).collect(Collectors.joining());
        final ErrorFlower errorFlower = new ErrorFlower(message, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorFlower, HttpStatus.NOT_FOUND);
    }
}
