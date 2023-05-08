package com.groupe4.pidev.repositories;



import com.groupe4.pidev.entities.Competence;
import com.groupe4.pidev.entities.Mymission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface MymissionRepository extends JpaRepository<Mymission, Long> {

    @Query("select count(ms) from Mymission ms join ms.users where ms.id= :id")
    Long getNbUsers(Long id);

    @Query("select (cmp) from Mymission ms join ms.competences cmp where ms.id=:id")
    Set<Competence> getCompetenceFromMission(Long id);

}