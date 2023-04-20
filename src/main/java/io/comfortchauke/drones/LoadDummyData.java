package io.comfortchauke.drones;

import io.comfortchauke.drones.enums.DroneModel;
import io.comfortchauke.drones.enums.DroneState;
import io.comfortchauke.drones.model.dto.DroneLoadMedicationDto;
import io.comfortchauke.drones.model.dto.DroneRegistrationDto;
import io.comfortchauke.drones.model.dto.MedicationDto;
import io.comfortchauke.drones.service.DroneLogService;
import io.comfortchauke.drones.service.DroneService;
import io.comfortchauke.drones.service.MedicationService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoadDummyData {
    private final DroneService droneService;
    private final MedicationService medicationService;
    private final DroneLogService logService;
    @PostConstruct
    public void init(){
        //run transactions to create some test data
        MedicationDto medicine=new MedicationDto("TEST-MEDICATION1","MM1",imageStr);
        medicationService.saveMedication(medicine);
        medicine=new MedicationDto("TEST-MEDICATION2","MM2",imageStr);
        medicationService.saveMedication(medicine);
        medicine=new MedicationDto("TEST-MEDICATION3","MM3",imageStr);
        medicationService.saveMedication(medicine);
        DroneRegistrationDto drone=new DroneRegistrationDto(DroneModel.HEAVY_WEIGHT,450,"SS6");
        droneService.register(drone);
        drone=new DroneRegistrationDto(DroneModel.HEAVY_WEIGHT,400,"SS1");
        droneService.register(drone);
        drone=new DroneRegistrationDto(DroneModel.HEAVY_WEIGHT,350,"SS2");
        droneService.register(drone);
        drone=new DroneRegistrationDto(DroneModel.MIDDLE_WEIGHT,400,"SS3");
        droneService.register(drone);
        drone=new DroneRegistrationDto(DroneModel.LIGHT_WEIGHT,250,"SS4");
        droneService.register(drone);
        drone=new DroneRegistrationDto(DroneModel.LIGHT_WEIGHT,150,"SS5");
        droneService.register(drone);

        //Update all battery status to allow loading
        logService.updateBattery();

        droneService.setState("SS1", DroneState.LOADING);
        droneService.setState("SS2", DroneState.LOADING);
        droneService.setState("SS3", DroneState.LOADING);
        droneService.setState("SS4", DroneState.LOADING);
        droneService.setState("SS5", DroneState.LOADING);
        droneService.setState("SS6", DroneState.LOADING);

        DroneLoadMedicationDto
                load=new DroneLoadMedicationDto("SS1","Some medicine","MM1",40);
        droneService.load(load);
        load=new DroneLoadMedicationDto("SS1","Some medicine","MM2",70);
        droneService.load(load);
        load=new DroneLoadMedicationDto("SS1","Some medicine","MM3",10);
        droneService.load(load);


        load=new DroneLoadMedicationDto("SS2","Some medicine","MM1",40);
        droneService.load(load);
        load=new DroneLoadMedicationDto("SS2","Some medicine","MM2",70);
        droneService.load(load);
        load=new DroneLoadMedicationDto("SS2","Some medicine","MM3",10);
        droneService.load(load);


        load=new DroneLoadMedicationDto("SS3","Some medicine","MM1",40);
        droneService.load(load);
        load=new DroneLoadMedicationDto("SS3","Some medicine","MM2",70);
        droneService.load(load);


        load=new DroneLoadMedicationDto("SS4","Some medicine","MM1",40);
        droneService.load(load);


        load=new DroneLoadMedicationDto("SS5","Some medicine","MM1",40);
        droneService.load(load);
        load=new DroneLoadMedicationDto("SS5","Some medicine","MM2",70);
        droneService.load(load);
        load=new DroneLoadMedicationDto("SS5","Some medicine","MM3",10);
        droneService.load(load);


        load=new DroneLoadMedicationDto("SS6","Some medicine","MM1",40);
        droneService.load(load);
        load=new DroneLoadMedicationDto("SS6","Some medicine","MM2",30);
        droneService.load(load);
        load=new DroneLoadMedicationDto("SS6","Some medicine","MM3",60);
        droneService.load(load);


        load=new DroneLoadMedicationDto("SS6","Some medicine","MM1",25);
        droneService.load(load);
        load=new DroneLoadMedicationDto("SS6","Some medicine","MM2",75);
        droneService.load(load);
        load=new DroneLoadMedicationDto("SS6","Some medicine","MM3",100);
        droneService.load(load);

        droneService.setState("SS1", DroneState.LOADED);
        droneService.setState("SS2", DroneState.LOADED);
        droneService.setState("SS3", DroneState.LOADED);
        droneService.setState("SS4", DroneState.LOADED);
        droneService.setState("SS5", DroneState.LOADED);
        droneService.setState("SS6", DroneState.LOADED);
    }



    String imageStr="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";
}
