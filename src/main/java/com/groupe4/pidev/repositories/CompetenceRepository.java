package com.groupe4.pidev.repositories;



import com.groupe4.pidev.entities.Competence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetenceRepository extends JpaRepository<Competence, Long> {
    Competence findCompetenceByCname(String cname);
}
