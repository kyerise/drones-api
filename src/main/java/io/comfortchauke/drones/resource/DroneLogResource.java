package io.comfortchauke.drones.resource;

import io.comfortchauke.drones.model.DroneLog;
import io.comfortchauke.drones.service.DroneLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("log")
@RequiredArgsConstructor
public class DroneLogResource {
    private final DroneLogService droneLogService;
    @GetMapping
    public List<DroneLog> listRecentLogs() {
        return droneLogService.getLastLogs(0,10);
    }
    @GetMapping("{page}/{size}")
    public List<DroneLog> selectLogs(@PathVariable int page,@PathVariable int size) {
        return droneLogService.getLastLogs(page,size);
    }
}
