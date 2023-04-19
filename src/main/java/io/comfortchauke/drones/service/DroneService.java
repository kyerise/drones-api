package io.comfortchauke.drones.service;

import io.comfortchauke.drones.dao.DroneDao;
import io.comfortchauke.drones.dao.MedicationDao;
import io.comfortchauke.drones.dao.MedicationPackageDao;
import io.comfortchauke.drones.enums.DroneState;
import io.comfortchauke.drones.exceptions.DroneResourceNotFoundException;
import io.comfortchauke.drones.exceptions.DroneTransactionForbiddenException;
import io.comfortchauke.drones.model.Drone;
import io.comfortchauke.drones.model.Medication;
import io.comfortchauke.drones.model.MedicationPackage;
import io.comfortchauke.drones.model.dto.DroneDto;
import io.comfortchauke.drones.model.dto.DroneLoadMedicationDto;
import io.comfortchauke.drones.model.dto.DroneRegistrationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DroneService {

    private final MedicationDao medicationDao;
    private final DroneDao droneDao;
    private final MedicationPackageDao medicationPackageDao;

    public DroneDto register(DroneRegistrationDto drone) {
        Drone entity=new Drone();
        entity.setState(DroneState.IDLE);
        entity.setModel(drone.getModel());
        entity.setWeightLimit(drone.getWeightLimit());
        entity.setSerialNumber(drone.getSerialNumber());
        droneDao.save(entity);
        return checkDrone(entity.getSerialNumber());
    }

    public DroneLoadMedicationDto load(DroneLoadMedicationDto loadedMedication) {
        DroneDto drone = checkDrone(loadedMedication.getDroneSerial());
        if(drone.getState()!=DroneState.LOADING){
            throw new DroneTransactionForbiddenException(String.format("Drone in %s not in LOADING state",drone.getState()));
        }
        if(drone.getWeight()+loadedMedication.getWeight()>drone.getWeightLimit()){
            throw new DroneTransactionForbiddenException("weight limit will be exceeded");
        }
        Optional<Medication> medication = medicationDao.findByCode(loadedMedication.getMedicationCode());
        if(medication.isEmpty()){
            throw new DroneResourceNotFoundException(String.format("medication with code: %s is undefined",loadedMedication.getMedicationCode()));
        }
        MedicationPackage pkg=new MedicationPackage();
        pkg.setDrone(droneDao.findBySerialNumber(drone.getSerialNumber()).get());
        pkg.setMedication(medication.get());
        pkg.setWeight(loadedMedication.getWeight());
        pkg=medicationPackageDao.save(pkg);
        return pkg.getDroneLoadMedicationDto();
    }

    public List<DroneLoadMedicationDto> checkLoaded(String droneSerial) {
        return droneDao.findBySerialNumber(droneSerial).stream()
                .flatMap(d -> medicationPackageDao.findByDrone(d).stream())
                .map(m -> m.getDroneLoadMedicationDto())
                .collect(Collectors.toList());
    }

    public DroneDto checkDrone(String droneSerial) {
        Optional<Drone> drone1 = droneDao.findBySerialNumber(droneSerial);
        if(drone1.isEmpty()){
            throw new DroneResourceNotFoundException(String.format("Drone with serial: %s not found",droneSerial));
        }
        int weight=medicationPackageDao.findByDrone(drone1.get()).stream().mapToInt(m->m.getWeight()).sum();
        DroneDto dto=drone1.get().getDroneDto();
        dto.setWeight(weight);
        return dto;
    }

    public List<DroneDto> checkAvailable() {
        return droneDao.findAll().stream().filter(drone -> drone.isAvailable()).map(d -> checkDrone(d.getSerialNumber())).collect(Collectors.toList());
    }

    public boolean setState(String droneSerial, DroneState state) {
        Optional<Drone> drone=droneDao.findBySerialNumber(droneSerial);
        if(drone.isEmpty()){
            throw new DroneResourceNotFoundException(String.format("Drone with serial: %s not found",droneSerial));
        }
        if(state==DroneState.LOADING&&drone.get().getBatteryCapacity()<25){
            throw new DroneTransactionForbiddenException("Battery too low for loading");
        }
        if(drone.get().getState()==state){
            return false;
        }
        drone.get().setState(state);
        droneDao.save(drone.get());
        return true;
    }

    public List<DroneDto> listAll() {
        return droneDao.findAll().stream().map(d->checkDrone(d.getSerialNumber())).collect(Collectors.toList());
    }
}
