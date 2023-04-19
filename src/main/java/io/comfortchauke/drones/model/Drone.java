package io.comfortchauke.drones.model;

import io.comfortchauke.drones.enums.DroneModel;
import io.comfortchauke.drones.enums.DroneState;
import io.comfortchauke.drones.model.dto.DroneDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "drone")
@Data
public class Drone extends AbstractEntity {
    @Id
    @Column(name = "serial_number")
    private String serialNumber;
    @Column(name = "weight_limit")
    private int weightLimit;
    @Max(value = 100, message = "{validation.drone.batteryMaxError}")
    @Min(value = 0, message = "{validation.drone.batteryMinError}")
    private int batteryCapacity;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "{validation.drone.stateNotNull}")
    private DroneState state;
    @Enumerated(EnumType.STRING)
    private DroneModel model;
    public DroneDto getDroneDto(){
        return new DroneDto(model,weightLimit,0,serialNumber,state);
    }
    public DroneLog getDroneLog(){
        return new DroneLog(0,serialNumber,batteryCapacity, Instant.now());
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public boolean isAvailable() {
        return state==DroneState.LOADING||state==DroneState.IDLE;
    }
}
