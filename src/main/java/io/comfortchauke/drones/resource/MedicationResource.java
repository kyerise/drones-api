package io.comfortchauke.drones.resource;

import io.comfortchauke.drones.model.Medication;
import io.comfortchauke.drones.model.dto.MedicationDto;
import io.comfortchauke.drones.service.MedicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medication")
@RequiredArgsConstructor
public class MedicationResource {
    private final MedicationService service;

    @PostMapping("save")
    public MedicationDto saveMedication(@RequestBody @Valid MedicationDto medicine) {
        return service.saveMedication(medicine);
    }

    @GetMapping("list")
    public List<MedicationDto> listMedication() {
        return service.listMedication();
    }

    @GetMapping(path = "image/{code}",produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable String code) {
        HttpHeaders headers = new HttpHeaders();
        byte[] body = service.getImage(code);
        if(body==null){
            return ResponseEntity.notFound().build();
        }
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.set("Content-Disposition", String.format("attachment; filename=%s_Image.jpg",code));
        headers.setContentLength(body.length);
        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }
}
