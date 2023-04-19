package io.comfortchauke.drones.dao;

import io.comfortchauke.drones.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicationDao extends JpaRepository<Medication, String> {
    Optional<Medication> findByCode(String medicationCode);
}
