package io.comfortchauke.drones.model;

import io.comfortchauke.drones.model.dto.DroneLoadMedicationDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "medication_package")
@Data
public class MedicationPackage extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "weight")
    private int weight;
    @ManyToOne
    @JoinColumn(name = "medication_code")
    private Medication medication;
    @ManyToOne
    @JoinColumn(name = "drone_serial")
    private Drone drone;

    public DroneLoadMedicationDto getDroneLoadMedicationDto(){
        return new DroneLoadMedicationDto(drone.getSerialNumber(),medication.getName(),medication.getCode(),weight);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedicationPackage that = (MedicationPackage) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}

