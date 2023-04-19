package io.comfortchauke.drones.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@NoArgsConstructor
@AllArgsConstructor
public class DroneLoadMedicationDto {
    private String droneSerial;
    private String medicationName;
    private String medicationCode;
    private int weight;
}
