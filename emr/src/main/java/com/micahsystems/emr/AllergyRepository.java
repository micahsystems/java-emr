package com.micahsystems.emr;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AllergyRepository extends JpaRepository<Allergy, Long> {
    List<Allergy> findByPatientId(Long patientId);
}
