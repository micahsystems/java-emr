package com.micahsystems.emr;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface VitalsRepository extends JpaRepository<Vitals, Long>{
    List<Vitals> findByEncounterId(Long encounterId);
}
