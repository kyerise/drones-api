package io.comfortchauke.drones.resource;

import io.comfortchauke.drones.enums.DroneState;
import io.comfortchauke.drones.model.dto.DroneDto;
import io.comfortchauke.drones.model.dto.DroneLoadMedicationDto;
import io.comfortchauke.drones.model.dto.DroneRegistrationDto;
import io.comfortchauke.drones.service.DroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("drone")
@RequiredArgsConstructor
public class DroneResource {
    private final DroneService service;

    @PostMapping(path = "register")
    public DroneDto register(DroneRegistrationDto drone) {
        return service.register(drone);
    }

    @PostMapping(path = "loadmedication")
    public DroneLoadMedicationDto load(DroneLoadMedicationDto loadedMedication) {
        return service.load(loadedMedication);
    }
    @PostMapping(path = "setstate/{droneSerial}")
    public boolean setState(@PathVariable String droneSerial, DroneState state) {
        return service.setState(droneSerial,state);
    }

    @GetMapping("checkdrone/{droneSerial}")
    public DroneDto checkDrone(@PathVariable String droneSerial) {
        return service.checkDrone(droneSerial);
    }

    @GetMapping("checkloaded/{droneSerial}")
    public List<DroneLoadMedicationDto> checkLoaded(@PathVariable String droneSerial) {
        return service.checkLoaded(droneSerial);
    }

    @GetMapping("checkavailable")
    public List<DroneDto> checkAvailable() {
        return service.checkAvailable();
    }
    @GetMapping("")
    public List<DroneDto> listAll() {
        return service.listAll();
    }
}
