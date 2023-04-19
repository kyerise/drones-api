package io.comfortchauke.drones.service;

import io.comfortchauke.drones.model.Drone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class DroneHardwareService {
    /**
    Simulate interactions from the physical drones.
    The battery level will decrease continuously and be recharged randomly close to the low level
    *
    *
    State could also be simulated, but that becomes a stochastic queueing theory problem with interesting maths, little code
    * */
    private final Random random=new Random();

    public void updateDroneBattery(Drone drone) {
        int currentCapacity=drone.getBatteryCapacity();
        if(currentCapacity<5){
            drone.setBatteryCapacity(80+ random.nextInt(19));
            return;
        }
        int level=random.nextInt(currentCapacity+25);
        if(level>currentCapacity){
            drone.setBatteryCapacity(80+ random.nextInt(19));
            return;
        }
        drone.setBatteryCapacity(currentCapacity-random.nextInt(5));
        return;
    }
}
