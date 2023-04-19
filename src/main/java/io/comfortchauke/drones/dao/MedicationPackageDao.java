package io.comfortchauke.drones.dao;

import io.comfortchauke.drones.model.Drone;
import io.comfortchauke.drones.model.MedicationPackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationPackageDao extends JpaRepository<MedicationPackage, Long> {
    List<MedicationPackage> findByDrone(Drone drone);
}
