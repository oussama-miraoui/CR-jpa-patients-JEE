package miraoui.ma.jpaappatients;

import lombok.AllArgsConstructor;
import lombok.Data;
import miraoui.ma.jpaappatients.entities.Patient;
import miraoui.ma.jpaappatients.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class JpaApPatientsApplication implements CommandLineRunner {


    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(JpaApPatientsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        for (int i=0; i<25; i++){
            patientRepository.save(
                    new Patient(null, "hassan"+i, new Date(), true ,(int)(Math.random()*999)));
        }
        /*patientRepository.save(
                new Patient(null, "hassan", new Date(),false,56));
        patientRepository.save(
                new Patient(null,"Mohammed", new Date(),true,100));
        patientRepository.save(
                new Patient(null,"Imane",new Date(),false,11));*/

        Page<Patient> patients = patientRepository.findAll(PageRequest.of(0, 5));
        System.out.println("total pages: "+patients.getTotalPages());
        System.out.println("total elements: "+patients.getTotalElements());
        System.out.println("page num: "+ patients.getNumber());

        List<Patient> content = patients.getContent();
        content.forEach(p -> {
            System.out.println("==============");
            System.out.println(p.getNom());
            System.out.println(p.getDateNaissance());
            System.out.println(p.isMalade());
            System.out.println(p.getScore());

        });

        Patient patient = patientRepository.findById(1L).orElse(null);

        if(patient != null){
            System.out.println(patient.getNom());
            System.out.println(patient.isMalade());
        }

        //delete
        patientRepository.deleteById(3L);


        List<Patient> patientMalade = patientRepository.findByMaladeIsTrue();

        patientMalade.forEach(p->{
            System.out.println(p.getNom());
        });

        // patientRepository.findByMaladeAndScoreLessThan(true,100);

        List<Patient> patientList = patientRepository.chercherPatients("%ha%", 500);

        patientList.forEach(p->{
            System.out.println(p.getScore());
        });
    }
}
