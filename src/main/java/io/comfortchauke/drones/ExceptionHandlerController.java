package io.comfortchauke.drones;

import io.comfortchauke.drones.exceptions.DroneResourceNotFoundException;
import io.comfortchauke.drones.exceptions.DroneTransactionForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(DroneResourceNotFoundException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    @ResponseBody
    public String droneNotFoundException(DroneResourceNotFoundException e) {
        return e.getLocalizedMessage();
    }
    @ExceptionHandler(DroneTransactionForbiddenException.class)
    @ResponseStatus(value= HttpStatus.FORBIDDEN)
    @ResponseBody
    public String droneTransactionForbiddenException(DroneTransactionForbiddenException e) {
        return e.getLocalizedMessage();
    }
}