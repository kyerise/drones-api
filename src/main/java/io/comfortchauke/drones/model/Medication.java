package io.comfortchauke.drones.model;

import io.comfortchauke.drones.model.dto.MedicationDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name = "medication")
@Data
public class Medication extends AbstractEntity {
    @Column(name = "name")
    private String name;
    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "image")
    @Lob
    private byte[] image;

    public MedicationDto getMedicationDto() {
        return new MedicationDto(name, code,"http://localhost:8080/medication/image/"+code);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

