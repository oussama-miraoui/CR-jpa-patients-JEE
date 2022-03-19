package miraoui.ma.jpaappatients.repositories;

import miraoui.ma.jpaappatients.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    public List<Patient> findByMaladeIsTrue();
}
