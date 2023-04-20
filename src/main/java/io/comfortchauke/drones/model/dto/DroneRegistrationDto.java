package io.comfortchauke.drones.model.dto;

import io.comfortchauke.drones.enums.DroneModel;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneRegistrationDto {
    @NotNull(message = "{validation.drone.modelNotNull}")
    private DroneModel model;
    @Max(value = 500, message = "{validation.drone.weightLimitError}")
    private int weightLimit;
    @Size(max = 100, message = "{validation.drone.serial.maxlenError}")
    @Size(min = 1, message = "{validation.drone.serial.minlenError}")
    private String serialNumber;
}
