package io.comfortchauke.drones.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MedicationDto {
    @Pattern(regexp = "^[a-zA-Z0-9_-]*$", message = "${validation.medication.nameError}")
    @NotBlank( message = "${validation.medication.nameError}")
    private String name;
    @Pattern(regexp = "^[A-Z0-9_]*$", message = "{validation.medication.codeError}")
    @NotBlank(message = "{validation.medication.codeError}")
    private String code;
    private String image;

}
