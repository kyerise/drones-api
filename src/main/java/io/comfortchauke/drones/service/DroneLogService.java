package io.comfortchauke.drones.service;

import io.comfortchauke.drones.dao.DroneDao;
import io.comfortchauke.drones.dao.DroneLogDao;
import io.comfortchauke.drones.model.Drone;
import io.comfortchauke.drones.model.DroneLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.support.PageableUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DroneLogService {
    private final DroneDao droneDao;
    private final DroneLogDao droneLogDao;
    private final DroneHardwareService hardwareService;

    @Scheduled(initialDelay = 2000l,fixedRate = 5000l)
    public void updateBattery(){
        log.info("checking drones:");
        List<Drone> updatedDrones = droneDao.findAll().stream().map(drone -> {
            hardwareService.updateDroneBattery(drone);
            return droneDao.save(drone);
        }).collect(Collectors.toList());
        droneDao.saveAll(updatedDrones);
        List<DroneLog> logs = updatedDrones.stream().map(drone -> drone.getDroneLog()).collect(Collectors.toList());
        droneLogDao.saveAll(logs);
    }
    public List<DroneLog> getLastLogs(int page,int size){
        Pageable pageable= PageRequest.of(page,size, Sort.Direction.DESC,"id");
        return droneLogDao.findAll(pageable).getContent();
    }
}
