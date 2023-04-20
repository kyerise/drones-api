package io.comfortchauke.drones.service;

import io.comfortchauke.drones.model.Drone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DroneHardwareServiceTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void updateDroneBattery() {
        Drone drone=new Drone();
        DroneHardwareService service=new DroneHardwareService();
        drone.setBatteryCapacity(4);
        service.updateDroneBattery(drone);
        assertTrue(drone.getBatteryCapacity()>79);
    }
}