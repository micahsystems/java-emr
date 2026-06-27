package com.micahsystems.emr;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "lab_results")
public class LabResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "encounter_id", nullable = false)
    private Encounter encounter;
    @Column(name = "test_name", nullable = false)
    private String testName;
    @Column(name = "result_value")
    private String resultValue;
    private String unit;
    @Column(name = "reference_range")
    private String referenceRange;
    @Column(name = "result_date", nullable = false)
    private LocalDate resultDate;
    public LabResult() {}
    public Long getId() { return id; }
    public Encounter getEncounter() { return encounter; }
    public void setEncounter(Encounter encounter) { this.encounter = encounter; }
    public String getTestName() { return testName; }
    public void setTestName(String testName) { this.testName = testName; }
    public String getResultValue() { return resultValue; }
    public void setResultValue(String resultValue) { this.resultValue = resultValue; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public String getReferenceRange() { return referenceRange; }
    public void setReferenceRange(String referenceRange) { this.referenceRange = referenceRange; }
    public LocalDate getResultDate() { return resultDate; }
    public void setResultDate(LocalDate resultDate) { this.resultDate = resultDate; }
}
