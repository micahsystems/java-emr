package com.micahsystems.emr;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long>{
    List<Diagnosis> findByEncounterId(Long encounterId);
}
