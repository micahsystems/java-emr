package com.micahsystems.emr;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/lab-results")
public class LabResultController {
    private final LabResultRepository labResultRepository;
    private final EncounterRepository encounterRepository;
    public LabResultController(LabResultRepository labResultRepository,
                               EncounterRepository encounterRepository) {
        this.labResultRepository = labResultRepository;
        this.encounterRepository = encounterRepository;
    }
    @GetMapping
    public List<LabResult> getAllLabResults() {
        return labResultRepository.findAll();
    }
    @GetMapping("/encounter/{encounterId}")
    public List<LabResult> getLabResultsByEncounter(@PathVariable Long encounterId) {
        return labResultRepository.findByEncounterId(encounterId);
    }
    @PostMapping
    public ResponseEntity<LabResult> createLabResult(@RequestBody LabResultRequest request) {
        return encounterRepository.findById(request.getEncounterId())
                .map(encounter -> {
                    LabResult lab = new LabResult();
                    lab.setEncounter(encounter);
                    lab.setTestName(request.getTestName());
                    lab.setResultValue(request.getResultValue());
                    lab.setUnit(request.getUnit());
                    lab.setReferenceRange(request.getReferenceRange());
                    lab.setResultDate(request.getResultDate());
                    return ResponseEntity.ok(labResultRepository.save(lab));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLabResult(@PathVariable Long id) {
        if (labResultRepository.existsById(id)) {
            labResultRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
