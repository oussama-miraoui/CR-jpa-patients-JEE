package miraoui.ma.jpaappatients.repositories;

import miraoui.ma.jpaappatients.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByMaladeIsTrue();
    //
    List<Patient> findByMaladeAndScoreLessThan(boolean m, int score);
    //
    @Query("select p from Patient p where p.nom like :x and p.score > :y")
    List<Patient> chercherPatients(
            @Param("x") String nom,
            @Param("y") int score
    );
}
