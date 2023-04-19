package io.comfortchauke.drones.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DroneTransactionForbiddenException extends RuntimeException{
    public DroneTransactionForbiddenException(String msg){
        super(msg);
    }
}
