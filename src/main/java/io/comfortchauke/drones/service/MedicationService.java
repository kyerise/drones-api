package io.comfortchauke.drones.service;

import io.comfortchauke.drones.dao.MedicationDao;
import io.comfortchauke.drones.exceptions.DroneTransactionForbiddenException;
import io.comfortchauke.drones.model.Medication;
import io.comfortchauke.drones.model.dto.MedicationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicationService {
    private final MedicationDao dao;

    public MedicationDto saveMedication(MedicationDto medicine) {
        Optional<Medication> medication1 = dao.findById(medicine.getCode());
        if (medication1.isPresent()) {
            throw new DroneTransactionForbiddenException("Medicine Code already exist:" + medicine.getCode());
        }
        Medication medication = new Medication();
        medication.setCode(medicine.getCode());
        medication.setName(medicine.getName());
        byte[] data = Base64.getDecoder().decode(medicine.getImage());
        medication.setImage(data);
        log.info("medication: name:{}, code:{}, image:{}",medication.getName(),medication.getCode(),medication.getImage().length);
        medication = dao.save(medication);
        return medication.getMedicationDto();
    }

    public List<MedicationDto> listMedication() {
        return dao.findAll().stream().map(m -> m.getMedicationDto()).collect(Collectors.toList());
    }

    public byte[] getImage(String code) {
        Optional<Medication> medication = dao.findById(code);
        if (medication.isPresent()) {
            return medication.get().getImage();
        }
        return null;
    }
}
