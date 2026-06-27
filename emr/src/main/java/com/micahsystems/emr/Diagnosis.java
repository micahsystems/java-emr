package com.micahsystems.emr;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "diagnoses")
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "encounter_id", nullable = false)
    private Encounter encounter;
    @Column(name = "icd10_code", nullable = false)
    private String icd10Code;
    @Column(nullable = false)
    private String description;
    @Column(name = "diagnosed_date", nullable = false)
    private LocalDate diagnosedDate;
    public Diagnosis() {}
    public Long getId() { return id; }
    public Encounter getEncounter() { return encounter; }
    public void setEncounter(Encounter encounter) { this.encounter = encounter; }
    public String getIcd10Code() { return icd10Code; }
    public void setIcd10Code(String icd10Code) { this.icd10Code = icd10Code; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getDiagnosedDate() { return diagnosedDate; }
    public void setDiagnosedDate(LocalDate diagnosedDate) { this.diagnosedDate = diagnosedDate; }
}
