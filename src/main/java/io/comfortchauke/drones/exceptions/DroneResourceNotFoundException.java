package io.comfortchauke.drones.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DroneResourceNotFoundException extends RuntimeException{
    public DroneResourceNotFoundException(String msg){
        super(msg);
    }
}
