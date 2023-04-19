package io.comfortchauke.drones.model.dto;

import io.comfortchauke.drones.enums.DroneModel;
import io.comfortchauke.drones.enums.DroneState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneDto {
    private DroneModel model;
    private int weightLimit;
    private int weight;
    private String serialNumber;
    private DroneState state;
}
