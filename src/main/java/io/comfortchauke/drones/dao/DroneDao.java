package io.comfortchauke.drones.dao;

import io.comfortchauke.drones.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DroneDao extends JpaRepository<Drone, String> {
    Optional<Drone> findBySerialNumber(String serialNumber);
}
