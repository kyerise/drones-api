package io.comfortchauke.drones.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "drone_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneLog extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long id;
    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "battery_capacity")
    private int batteryCapacity;
    @Column(updatable = false, nullable = false)
    private Instant readTime=Instant.now();
}
