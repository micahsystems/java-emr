package com.micahsystems.emr;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/vitals")
public class VitalsController {
    private final VitalsRepository vitalsRepository;
    private final EncounterRepository encounterRepository;
    public VitalsController(VitalsRepository vitalsRepository,
                            EncounterRepository encounterRepository) {
        this.vitalsRepository = vitalsRepository;
        this.encounterRepository = encounterRepository;
    }
    @GetMapping
    public List<Vitals> getAllVitals() {
        return vitalsRepository.findAll();
    }
    @GetMapping("/encounter/{encounterId}")
    public List<Vitals> getVitalsByEncounter(@PathVariable Long encounterId) {
        return vitalsRepository.findByEncounterId(encounterId);
    }
    @PostMapping
    public ResponseEntity<Vitals> createVitals(@RequestBody VitalsRequest request) {
        return encounterRepository.findById(request.getEncounterId())
                .map(encounter -> {
                    Vitals vitals = new Vitals();
                    vitals.setEncounter(encounter);
                    vitals.setRecordedDate(request.getRecordedDate());
                    vitals.setBloodPressure(request.getBloodPressure());
                    vitals.setHeartRate(request.getHeartRate());
                    vitals.setTemperature(request.getTemperature());
                    vitals.setWeight(request.getWeight());
                    vitals.setHeight(request.getHeight());
                    return ResponseEntity.ok(vitalsRepository.save(vitals));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVitals(@PathVariable Long id) {
        if (vitalsRepository.existsById(id)) {
            vitalsRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
