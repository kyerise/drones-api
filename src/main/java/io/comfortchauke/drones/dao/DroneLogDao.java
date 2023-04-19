package io.comfortchauke.drones.dao;

import io.comfortchauke.drones.model.Drone;
import io.comfortchauke.drones.model.DroneLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DroneLogDao extends JpaRepository<DroneLog, Long> {
    //Page<DroneLog> findAll(Pageable pageable);
}
