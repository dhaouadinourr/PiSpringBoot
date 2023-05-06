package com.groupe4.pidev.services;

import com.groupe4.pidev.entities.Competence;
import com.groupe4.pidev.entities.Mymission;

import java.util.List;
import java.util.Set;

public interface IMymissionService {
    Mymission ajouterMymission (Mymission Mymission);
    Mymission modifierMymission (Mymission Mymission);
    List<Mymission> afficherMymission();
    Mymission afficherMymission(Long id);
    void supprimerMymission(Long id);

    Mymission addMissionWithCompetence(Long idMission,Set<Long> idCompts);

    Mymission AssignUserToMission(Long idMission,String nameU);

    boolean verifMissionCapacity(Long idMission);

    Set<Competence> getCompetencesForMission(Long missionId);
}
