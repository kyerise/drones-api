package io.comfortchauke.drones.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

import java.time.Instant;

@MappedSuperclass
@Data
public class AbstractEntity {
    @JsonIgnore
    @Column(updatable = false, nullable = false)
    private Instant createTime;
    @JsonIgnore
    @Column(updatable = true, nullable = false)
    private Instant updateTime;

    //create and update time are just to demo audit features
    //createUser and lastModifyUser can be added here and enable jpa audit + spring security to set values for audit
    @PrePersist
    @PreUpdate
    public void init() {
        createTime = Instant.now();
        updateTime = Instant.now();
    }

}
